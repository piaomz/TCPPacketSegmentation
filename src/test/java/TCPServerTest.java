import org.example.TCPServer;

import java.io.IOException;

public class TCPServerTest {
    public static void main(String[] args) throws IOException {
        TCPServer server = new TCPServer();
        server.run();
    }
}
