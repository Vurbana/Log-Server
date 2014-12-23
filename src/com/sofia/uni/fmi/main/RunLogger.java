package com.sofia.uni.fmi.main;

import com.sofia.uni.fmi.server.TCPServer;

/**
 * Created by vankata on 23.12.14.
 */
public class RunLogger {
    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.startServer();
    }
}
