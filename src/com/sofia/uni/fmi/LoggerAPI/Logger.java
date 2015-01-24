package com.sofia.uni.fmi.LoggerAPI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import java.util.List;

/**
 * Created by vankata on 27.12.14.
 */
public class Logger {

    private static long uniqueNumber=0l;
    private final int PORT = 10514;
    private Socket socket;

    public  void logMessage(String msg) throws IOException {
        try {
            openSocket();
             try (BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                String indentifier = String.format("%012d\n", uniqueNumber++);
                socketWriter.write(indentifier);
                socketWriter.write(msg);
            }
        }finally {
            closeSocket();
        }

    }

    public  void logMessage(List<String> list) throws  IOException{
            try {
                openSocket();

                try (BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    String indentifier = String.format("%012d\n", uniqueNumber++);
                    socketWriter.write(indentifier);
                    for (String s : list) {
                        socketWriter.write(s);
                    }

                }
            }finally {
                closeSocket();
            }
        }

    private  void openSocket() throws IOException {
        socket = new Socket("localhost", PORT);
    }
    private  void closeSocket() throws IOException {
        socket.close();
}
}
