import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer {
    public static void main(String[] args) {
        int port = 8080; // You can change the port number if needed

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Knock Knock Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        if (inputLine.equalsIgnoreCase("Doris locked, that's why I'm knocking!")) {
                            out.println("Who's there?");
                        } else if (inputLine.equalsIgnoreCase("Doris")) {
                            out.println("Doris who?");
                        } else if (inputLine.equalsIgnoreCase("Doris locked, that's why I'm knocking!")) {
                            out.println("Haha! Good one!");
                        } else {
                            out.println("Say 'Knock, knock' to start the joke!");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Exception caught when trying to listen on port " + port);
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port " + port);
            System.out.println(e.getMessage());
        }
    }
}