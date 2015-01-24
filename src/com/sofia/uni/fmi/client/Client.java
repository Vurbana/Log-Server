package com.sofia.uni.fmi.client;

import com.sofia.uni.fmi.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vankata on 24.01.15.
 */
public class Client implements Runnable {
    private Logger log;
    private String msg;
    private List<String> msgs;
    public Client(String msg){
        log = new Logger();
        setMsg(msg);

    }
    public Client(List<String> list){
        log = new Logger();
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
            if(msg != null){
                log.logMessage(msg);
            }
            else{
                log.logMessage(msgs);
            }
        }catch (IOException e){
            e.printStackTrace();

        }
    }
}
