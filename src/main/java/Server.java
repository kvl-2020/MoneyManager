import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    static final int MY_PORT = 8989;

    public Server() {

        List<Buy> buys = new ArrayList<>();
        Summator summator = new Summator(buys);

        System.out.println("Стартуем сервер");
        try (ServerSocket serverSocket = new ServerSocket(MY_PORT);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                ) {
                    // обработка одного подключения
                    String inputStr = in.readLine();
                    System.out.println(inputStr);
                    JsonObject jsonObject = new JsonParser().parse(inputStr).getAsJsonObject();

                    String title = jsonObject.get("title").getAsString();
                    int sum = jsonObject.get("sum").getAsInt();
                    String date_ = jsonObject.get("date").getAsString();
                    String[] date = date_.split("\\.");
                    String dateY = date[0];
                    String dateM = date[1];
                    String dateD = date[2];
                    out.println(summator.add(new Buy(title, dateY, dateM, dateD, sum)));
                    out.flush();

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
