package com.sofia.uni.fmi.client;

import com.sofia.uni.fmi.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by vankata on 27.12.14.
 */
public class ClientRequests {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.submit(new Client("I love popcorn\n"));

        }
        for (int i = 0; i < 100; i++) {

            service.submit(new Client("I love vacations\n"));
        }
        ArrayList<String> msg = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            msg.add("Testing my app\n");
        }
        service.submit(new Client(msg));
        long time = System.currentTimeMillis();
        service.shutdown();
        try {

            if (service.awaitTermination(60, TimeUnit.SECONDS)) {
                System.out.println("It terminated");
                long finishTime = System.currentTimeMillis() - time;
                System.out.println("Time in ms:" + finishTime);

            } else {
                service.shutdownNow();
            }
        }catch (InterruptedException e){

        }


    }
}
