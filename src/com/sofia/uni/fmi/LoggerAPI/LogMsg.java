package com.sofia.uni.fmi.LoggerAPI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by vankata on 27.12.14.
 */
public class LogMsg {
    private static long uniqueNumber=0l;

    public static void logMessage(String msg) throws IOException{
        try(Socket socket = new Socket("localhost", 10514);){
            try(BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));){
                synchronized (socketWriter){
                    String indentifier = String.format("%012d\n", uniqueNumber++);
                    socketWriter.write(indentifier);
                    socketWriter.write(msg);
                }
            }

        }





    }
    public static void logMessages(List<String> list) throws  IOException{
        try(Socket socket = new Socket("localhost", 10514)){
            try(BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
                String indentifier = String.format("%012d\n", uniqueNumber++);
                synchronized (socketWriter){
                    socketWriter.write(indentifier);
                    for (String s:list){
                        socketWriter.write(s+"\n");
                    }
                }

            }
        }






    }
}
