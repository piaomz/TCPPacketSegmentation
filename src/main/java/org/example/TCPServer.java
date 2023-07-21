package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int MSS = 10;
    private static final int port = 8080;
    private ServerSocket serverSocket;
    Socket socket;

    public void run() throws IOException {
        //Using bufferreader to get message
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()))){
            while(true){
                //System.out.println("Waiting for message");
                //readLine can resolve 'TCP packet disaggregation'.
                String msg = bufferedReader.readLine();
                String msgCombine="";
                if (msg != null) {
                    try{
                        //resolve the header, get msg length
                        String msgLengthString = msg.substring(0,msg.indexOf("\t"));
                        int msgLength = Integer.parseInt(msgLengthString, 16);
                        //if a message is zero length, stop the running status.
                        if(msgLength==0){
                            break;
                        }
                        // place remain message in it.
                        msgCombine = msg.substring(msg.indexOf("\t")+1,msg.length());
                        //continue get msg till it get the length to it combined length
                        while(msgLength>msgCombine.length()){
                            msgCombine = msgCombine+ bufferedReader.readLine();
                        }
                    }catch(Exception e){
                        System.out.println("Error in finding msg length: "+e);
                    }
                    //postprocess of msg
                    msgCombine = msgCombine.replaceAll("\\\\n","\n");
                    System.out.println("Getting data from client：" + msgCombine);
                }
            }
        }
        //stop server
        socket.close();
        serverSocket.close();
    }
    public TCPServer() throws IOException {
        serverSocket = new ServerSocket(port);
        socket = serverSocket.accept();
    }

}
