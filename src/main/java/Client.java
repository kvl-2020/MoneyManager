import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private String host;
    private int port;

    private String sendString;

    public Client(String host, int port, String sendString) {
        this.host = host;
        this.port = port;
        this.sendString = sendString;

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println(sendString);
            String inputStr = in.readLine();
            System.out.println(inputStr);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}