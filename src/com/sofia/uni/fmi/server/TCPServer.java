package com.sofia.uni.fmi.server;

import com.sofia.uni.fmi.handler.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vankata on 23.12.14.
 */
public class TCPServer {
     private final int PORT = 10514;


    public void startServer(){
            try{
                ServerSocket serverSocket = new ServerSocket(PORT);
                while(true){
                    final Socket activeSocket = serverSocket.accept();
                    new Thread(new RequestHandler(activeSocket)).start();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

    }

}
