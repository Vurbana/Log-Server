package com.sofia.uni.fmi.client;

import com.sofia.uni.fmi.LoggerAPI.LogMsg;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vankata on 27.12.14.
 */
public class ClientRequests extends Thread {
    private String msg;
    private List<String> msgs;
    public ClientRequests(String msg){
        setMsg(msg);
    }
    public ClientRequests(List<String> list){
        setMsgs(list);
    }

    public void setMsg(String msg) {
        if(msg == null){
            this.msg = "";
        }
        else{
            this.msg = new String(msg);
        }
    }

    public void setMsgs(List<String> msgs) {
        if(msgs == null){
            this.msgs = new ArrayList<String>();
            msgs.add("");
        }
        else{
            this.msgs =  new ArrayList<String>(msgs);
        }
    }
    public void run() {
            try{
                LogMsg.logMessage(msg);
            }catch (IOException e){
                e.printStackTrace();
            }




    }

    public static void main(String[] args) {
        for (int i= 0; i<20; i++){
            new ClientRequests(RandomStringUtils.randomAlphabetic(15)+"\n").start();
        }




    }
}
