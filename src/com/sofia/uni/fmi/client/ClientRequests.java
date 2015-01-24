package com.sofia.uni.fmi.client;

import com.sofia.uni.fmi.LoggerAPI.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by vankata on 27.12.14.
 */
public class ClientRequests implements Runnable {
    private Logger log;
    private String msg;
    private List<String> msgs;
    public ClientRequests(String msg){
        log = new Logger();
        setMsg(msg);

    }
    public ClientRequests(List<String> list){
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

    public static void main(String[] args) throws  InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();


            for (int i = 0; i < 100; i++) {
               service.submit(new ClientRequests("I love popcorn\n"));
             //new Thread(new ClientRequests("I love popcorn \n")).start();
           }
            for (int i = 0; i < 100; i++) {
               //new Thread(new ClientRequests("I love vacations\n")).start();
                service.submit(new ClientRequests("I love vacations\n"));
            }
            ArrayList<String> msg = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                msg.add("Testing my app\n");
            }
        service.submit(new ClientRequests(msg));
        long time = System.currentTimeMillis();
        service.shutdown();
        if(service.awaitTermination(60, TimeUnit.SECONDS)){
            System.out.println("It terminated");
            long finishTime = System.currentTimeMillis() - time;
            System.out.println("Time in ms:" +finishTime);

        }

            //new Thread(new ClientRequests(msg)).start();




    }
}
