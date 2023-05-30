public class Sender {
    public static final String HOST = "localhost";
    private static final int PORT = 8989;

    public static void main(String[] args) {
        Client client1 = new Client(HOST, PORT,
                "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");
        Client client2 = new Client(HOST, PORT,
                "{\"title\": \"булка2\", \"date\": \"2023.02.08\", \"sum\": 400}");
        Client client3 = new Client(HOST, PORT,
                "{\"title\": \"тапки\", \"date\": \"2023.02.08\", \"sum\": 777}");
        Client client4 = new Client(HOST, PORT,
                "{\"title\": \"булка\", \"date\": \"2024.02.08\", \"sum\": 800}");
    }
}
