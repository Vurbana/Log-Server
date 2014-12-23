package com.sofia.uni.fmi.threads;

import java.net.Socket;


/**
 * Created by vankata on 23.12.14.
 */
public class RequestHandler extends Thread{
    private Socket socket = null;
    public RequestHandler(Socket s){
        this.socket = s;
    }
    public void run(){

    }
}
