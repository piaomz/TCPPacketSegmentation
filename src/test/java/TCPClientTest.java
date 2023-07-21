import org.example.TCPClient;

import java.io.IOException;

public class TCPClientTest {
    public static void main(String[] args) throws IOException {
        String[] messages = {"this is my message from \n client1",
                "this is my message from \n client2",
                "this is my message from \n client3",
                "this is my message from \n client4",
                "this is my message from \n client5",
                "this is my message from \n client6",
                "this is my message from \n client7",
                "this is my message from \n client8",
                "this is my message from \n client9",
                "this is my message from \n client10",
                ""
        };
        TCPClient client = new TCPClient(messages);
        client.send();
    }
}
