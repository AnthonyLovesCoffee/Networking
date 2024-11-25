import java.util.Scanner;

public class Main {

   // This method calculates the time it takes to transmit data for a given standard, protocol, and data rate
   private static double getTime(String standard, String protocol, double dataRate) {
      // Depending on the standard, instantiate the correct class and calculate the time
      if (standard.equals("g")) {
         Standard_11g standard_11g = new Standard_11g(4, 14, 14, 20, 10, 9, 34, 6);
         return standard_11g.time_calculator("g", protocol, dataRate); 
      } 
      else if (standard.equals("ac_w2")){
         Standard_11ac_w2 standard_11ac_w2 = new Standard_11ac_w2(3.6, 14, 14, 20, 10, 9, 40, 6);
         return standard_11ac_w2.time_calculator("ac_w2", protocol, dataRate); 
      }
      else if (standard.equals("ac_w2_best")) {
         Standard_11ac_w2 standard_11ac_w2_best = new Standard_11ac_w2(3.6, 14, 14, 92.8, 16, 9, 40, 0);
         return standard_11ac_w2_best.time_calculator("ac_w2_best", protocol, dataRate);
      }
      else if (standard.equals("ax")) {
         Standard_11ax standard_11ax = new Standard_11ax(13.6, 14, 14, 20, 16, 9, 40, 0);
         return standard_11ax.time_calculator("ax", protocol, dataRate);
      }
      else {
         Standard_11ax standard_11ax_best = new Standard_11ax(13.6, 14, 14, 92.8, 16, 9, 40, 0);
         return standard_11ax_best.time_calculator("ax_best", protocol, dataRate);
      }
   }

   // This method prints the calculated throughput and the time needed to transfer 15GB of data for a given protocol
   private static void standardPrint(double bytesTime_1500, String protocol) {
      // Calculate the actual data size after subtracting UDP/TCP headers
      int actualData = (protocol.equals("udp")) ? 1500 - 28 : 1500 - 40;

      // Print throughput and transmission time for 15GB of data
      System.out.println("The actual throughput: " +
              String.format("%.3f", Math.pow(10, 6) / bytesTime_1500 * 8 * actualData / Math.pow(1000, 2)) +
              " Mbps\n" + "The amount of time needed to transfer 15GB of data: " +
              String.format("%.3f", 15 * Math.pow(1024, 3) / actualData * bytesTime_1500 / Math.pow(10, 6)) + " Seconds");
   }

   //This method returns the minimum data rate for a given Wi-Fi standard.
   private static double getMinimumDataRate(String standard) {
      switch (standard) {
          case "g":
              return 6.0; // Normal/Best case 'g'
          case "ac_w2":
              return 7.2; // Normal case 'ac_w2'
          case "ac_w2_best":
              return 770.4; // Best case 'ac_w2'
          case "ax":
              return 8.6; // Normal case 'ax'
          case "ax_best":
              return 576.5; // Best case 'ax'
          default:
              System.out.println("Unknown standard for minimum data rate");
              return 0; 
      }
  }
  
   //This method returns the maximum data rate for a given Wi-Fi standard.
   private static double getMaximumDataRate(String standard) {
      switch (standard) {
          case "g":
              return 54.0; // Normal/Best case 'g'
          case "ac_w2":
              return 96.3; // Normal case 'ac_w2'
          case "ac_w2_best":
              return 6933.6; // Best case 'ac_w2'
          case "ax":
              return 143.4; // Normal case 'ax'
          case "ax_best":
              return 9607.8; // Best case 'ax'
          default:
              System.out.println("Unknown standard for maximum data rate");
              return 0; 
      }
  }
  
   public static void main(String[] args) {

      Scanner scanner = new Scanner(System.in);

      // User interface for selecting the Wi-Fi standard
      System.out.println("Select the Standard (1, 2, 3, 4, 5):");
      System.out.println("1. 802.11g (normal/best case)");
      System.out.println("2. 802.11ac_w2 (normal case)");
      System.out.println("3. 802.11ac_w2 (best case)");
      System.out.println("4. 802.11ax (normal case)");
      System.out.println("5. 802.11ax (best case)");
      int standardSelection = scanner.nextInt();

      // Determine the selected standard
      String standard = "";
      switch (standardSelection) {
          case 1:
              standard = "g";
              break;
          case 2:
              standard = "ac_w2";
              break;
          case 3:
              standard = "ac_w2_best";
              break;
          case 4:
              standard = "ax";
              break;
          case 5:
              standard = "ax_best";
              break;
          default:
              System.out.println("Invalid Standard");
              scanner.close();
              return; // exit if invalid selection
      }

      // User interface for selecting the data rate
      System.out.println("Select the Data Rate:");
      System.out.println("1. Minimum");
      System.out.println("2. Maximum");
      int dataRateSelection = scanner.nextInt();
      double dataRate = (dataRateSelection == 1) ? getMinimumDataRate(standard) : getMaximumDataRate(standard);

      // User interface for selecting UDP or TCP
      System.out.println("Select the Protocol:");
      System.out.println("1. UDP");
      System.out.println("2. TCP");
      int protocolSelection = scanner.nextInt();
      String protocol = (protocolSelection == 1) ? "udp" : "tcp";

      // Perform the calculation and display results
      try {
          double bytesTime_1500 = getTime(standard, protocol, dataRate);
          standardPrint(bytesTime_1500, protocol);
      } catch (Exception e) {
          System.out.println("An error occurred ");
      }

      scanner.close();
  }
}
