import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static final String CATEGORIES_FILE = "categories.tsv";

    private static final String OTHER = "другое";

    static final int MY_PORT = 8989;

    Categories categories = Categories.loadFromTxtFile(new File(CATEGORIES_FILE));

    public Server() {

        Map<String, Integer> dataSumm = new HashMap<>();
        Summator summator = new Summator(dataSumm);



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

                    out.println(summator.add(
                            getCategoriesByThing(jsonObject.get("title").getAsString()),
                            jsonObject.get("sum").getAsInt()));
                    out.flush();

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private String getCategoriesByThing(String thing) {
        if ( categories.getData().get(thing) != null ) {
            return categories.getData().get(thing);
        } else {
            return OTHER;
        }
    }
}
