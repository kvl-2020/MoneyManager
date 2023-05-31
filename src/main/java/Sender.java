public class Sender {
    public static final String HOST = "localhost";
    private static final int PORT = 8989;

    public static void main(String[] args) {
        Client client1 = new Client(HOST, PORT,
                "{\"title\": \"шапка\", \"date\": \"2021.01.01\", \"sum\": 10000}");
        Client client2 = new Client(HOST, PORT,
                "{\"title\": \"булка\", \"date\": \"2022.02.07\", \"sum\": 200}");
        Client client3 = new Client(HOST, PORT,
                "{\"title\": \"телевизор\", \"date\": \"2022.03.03\", \"sum\": 5000}");
        Client client4 = new Client(HOST, PORT,
                "{\"title\": \"мыло\", \"date\": \"2022.02.08\", \"sum\": 700}");
        Client client5 = new Client(HOST, PORT,
                "{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");
        Client client6 = new Client(HOST, PORT,
                "{\"title\": \"колбаса\", \"date\": \"2022.02.08\", \"sum\": 600}");
    }
}


