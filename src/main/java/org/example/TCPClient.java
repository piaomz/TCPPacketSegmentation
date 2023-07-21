package org.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    //this is the max packet(message) length, for safety, cannot smaller than 8, because we need a header to know how long the message is.
    private static final int MSS = 10;
    private static final String host = "127.0.0.1";
    private static final int port = 8080;
    private Socket socket;
    private OutputStream outputStream;

    private String[] messages;
    public void send() throws IOException {
        //send all msgs
        for(int i=0;i<messages.length;i++){
            //preprocess the message
            String msg = messages[i];
            msg = msg.replaceAll("\n","\\\\n");

            //header process
            String msglengthString = String.format("%08x", msg.length());
            String header = msglengthString+'\t';
            msg = header+'\t'+msg;

            //send msg in split format
            sendSplitMessage(msg);

        }
        //end tcp
        String msglengthString = String.format("%08x", 0);
        String endHeader = msglengthString+'\t';
        System.out.println("Sending: "+endHeader);
        outputStream.write((endHeader+'\n').getBytes());
        //release all resources
        System.out.println("Send Success");
        outputStream.close();
        socket.close();
    }
    public TCPClient(String[] messages) throws IOException {
        this.messages = messages;
        socket = new Socket(host, port);
        outputStream = socket.getOutputStream();
        System.out.println("Ready Sending");
    }
    private void sendSplitMessage(String msg) throws IOException {
        //split msg
        while(msg.length()>=MSS){
            String splitmsg = msg.substring(0,MSS);
            msg=msg.substring(MSS,msg.length());

            System.out.println("Sending: "+splitmsg);
            outputStream.write((splitmsg+'\n').getBytes());
        }
        //split end
        if(msg!=null || msg!=""){
            String splitmsg = msg;
            System.out.println("Sending: "+splitmsg);
            outputStream.write((splitmsg+'\n').getBytes());
        }
    }
}
