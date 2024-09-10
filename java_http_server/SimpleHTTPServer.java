import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleHTTPServer {

    public static void main(String[] args) throws Exception {
        final ServerSocket server = new ServerSocket(8080);
        System.out.println("Listening for connection on port 8080 ....");
        while (true) {
            final Socket clientSocket = server.accept();
            // our server will now accept and read HTTP requests
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();

            while(!line.isEmpty()){
                System.out.println(line);
                line = reader.readLine();
            }
        }
    }
}