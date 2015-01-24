package com.sofia.uni.fmi.server;

import com.sofia.uni.fmi.server.TCPServer;

/**
 * Created by vankata on 23.12.14.
 */
public class RunServer {
    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.startServer();
    }
}
