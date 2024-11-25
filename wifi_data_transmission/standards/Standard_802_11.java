import java.util.HashMap;
import java.util.Map;

public class Standard_802_11 {
   double Symbol_transit_time;
   int CTS_length;
   int ACK_length;
   double Preamble;
   int SIFS_time;
   int Slot_time;
   int DIFS_time;
   int MAC_header;
   int TCP_ACK_length;
   int Signal_Extension;
   int Spatial_Streams;

   // A hash to store the relationship between data rates and bits per OFDM symbol
   Map<Double, Double> ratesToBits = new HashMap<>();

   // Constructor to initialize the object with necessary parameters 
   Standard_802_11(double symbol_transit_time, int CTS_length, int ACK_length,
                   double preamble, int SIFS_time, int slot_time, int MAC_header, int Signal_Extension) {
      this.Symbol_transit_time = symbol_transit_time;
      this.CTS_length = CTS_length;
      this.ACK_length = ACK_length;
      this.Preamble = preamble;
      this.SIFS_time = SIFS_time;
      this.Slot_time = slot_time;
      this.DIFS_time = 2 * Slot_time + SIFS_time; // Calculate DIFS time based on Slot and SIFS times
      this.MAC_header = MAC_header;
      this.TCP_ACK_length = MAC_header + 8 + 20 + 20; // Calculate TCP ACK length including headers
      this.Signal_Extension = Signal_Extension;
   }

   // Initialize block size mappings for 802.11g/ac/ax for normal/best conditions
   private void ratesToBits_g_norm() {
      ratesToBits.put(6.0, 24.0);
      ratesToBits.put(54.0, 216.0);
   }

   private void ratesToBits_ac_norm() {
      ratesToBits.put(7.2, 26.0);
      ratesToBits.put(96.3, 346.6);
   }

   private void ratesToBits_ac_best() {
      ratesToBits.put(770.4, 2773.3);
      ratesToBits.put(6933.6, 24960.0);
   }

   private void ratesToBits_ax_norm() {
      ratesToBits.put(8.6, 117.0);
      ratesToBits.put(143.4, 1950.0);
   }

   private void ratesToBits_ax_best() {
      ratesToBits.put(576.5, 980.0);
      ratesToBits.put(9607.8, 16333.3);
   }

   // Method to calculate the total time required for a transmission 
   public double time_calculator(String standard, String tcp_udp, double data_rate) {
      double bits_per_symbol = 0;
      // Initialize block sizes based on the given standard
      if (standard.equals("g")) {
         ratesToBits_g_norm();
         bits_per_symbol = ratesToBits.get(data_rate);
      }
      // Similar initialization for other standards
      else if (standard.equals("ac_w2")) {
         ratesToBits_ac_norm();
         bits_per_symbol = ratesToBits.get(data_rate);
      }
      else if (standard.equals("ac_w2_best")) {
         ratesToBits_ac_best();
         bits_per_symbol = ratesToBits.get(data_rate);
      }
      else if (standard.equals("ax")) {
         ratesToBits_ax_norm();
         bits_per_symbol = ratesToBits.get(data_rate);
      }
      else if (standard.equals("ax_best")) {
         ratesToBits_ax_best();
         bits_per_symbol = ratesToBits.get(data_rate);
      }

      // Calculate times for different parts of the transmission process
      int RTS_length = 20; // Request to Send length

      // Calculation of time taken for the different transmission steps
      double RTS_time = (int)((RTS_length * 8 + 6) / bits_per_symbol + 1) * Symbol_transit_time;
      double CTS_time = (int)((CTS_length * 8 + 6) / bits_per_symbol + 1) * Symbol_transit_time;
      double ACK_time = (int)((ACK_length * 8 + 6) / bits_per_symbol + 1) * Symbol_transit_time;
      double TCP_ACK_time = (int)((TCP_ACK_length * 8 + 6) / bits_per_symbol + 1) * Symbol_transit_time;
      double DATA_time = (int)(((1500 + MAC_header + 8) * 8 + 6) / bits_per_symbol + 1) * Symbol_transit_time;
      
      // Sum up the total time based on whether the transmission is TCP or UDP
      double transmit_time = 0;
      if (tcp_udp.equals("udp")) {
         // Calculation for UDP transmission time
         transmit_time = DIFS_time + Preamble + RTS_time + Signal_Extension + SIFS_time + Preamble + CTS_time +
                 Signal_Extension + SIFS_time + Preamble + DATA_time + Signal_Extension + SIFS_time +
                 Preamble + ACK_time + Signal_Extension;
      }
      else if (tcp_udp.equals("tcp")) {
         // Calculation for TCP transmission time
         transmit_time = DIFS_time + Preamble + RTS_time + Signal_Extension + SIFS_time + Preamble +
                 CTS_time + Signal_Extension + SIFS_time + Preamble + DATA_time + Signal_Extension +
                 SIFS_time + Preamble + ACK_time + DIFS_time + Preamble + TCP_ACK_time +
                 Signal_Extension + SIFS_time + Preamble + ACK_time + Signal_Extension;
      }
      return transmit_time; // return total time
   }
}
