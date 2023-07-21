package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    private static final int MSS = 1024;
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8080;
        String message = "this is my message from client \npiaomz";
        Socket socket = new Socket("127.0.0.1", port);
        OutputStream outputStream = socket.getOutputStream();
        System.out.println("Sending");
        for(int i=0;i<10;i++){
            outputStream.write((message+Integer.toString(i)+'\n').getBytes());
            System.out.println(i);
        }
        System.out.println("Send Success");
        outputStream.close();
        socket.close();
    }
}
