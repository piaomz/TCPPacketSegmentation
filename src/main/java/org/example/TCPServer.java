package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int MSS = 1024;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))){
            while(true){
                //System.out.println("Waiting for message");
                String msg = bufferedReader.readLine();
                if (msg != null) {
                    System.out.println("Getting data from client：" + msg);
                }
            }
        }
        //inputStream.close();
        //socket.close();
        //serverSocket.close();

    }

}
