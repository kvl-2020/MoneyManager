import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    static final int MY_PORT = 8989;

    static final String fileName = "data.bin";

    public Server() {

        List<Buy> buyes = new ArrayList<>();
        checkFile(fileName);
        loadDataFromFile(buyes);
        Summator summator = new Summator(buyes);

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
                    Buy buy = new Buy(title, dateY, dateM, dateD, sum);
                    out.println(summator.add(buy));
                    out.flush();
                    addDataToFile(buy);

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private void checkFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addDataToFile(Buy buy) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            String text = buy.getTitle() + "\t" +
                    buy.getDateY() + "." + buy.getDateM() + "." + buy.getDateD() + "." + "\t" +
                    buy.getSum() + "\n";
            bw.write(text);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadDataFromFile(List<Buy> buys) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strBuy = line.split("\t");
                String[] date = strBuy[1].split("\\.");
                Buy buy = new Buy(strBuy[0], date[0], date[1], date[1], Integer.parseInt(strBuy[2]));
                buys.add(buy);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
