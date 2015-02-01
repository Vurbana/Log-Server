package com.sofia.uni.fmi.client;

import com.sofia.uni.fmi.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by vankata on 27.12.14.
 */
public class ClientRequests {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()){
            String message = input.nextLine();
            service.execute(new Client(message));

        }
        service.shutdown();
        try {
            if (!service.awaitTermination(10, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        }catch (InterruptedException e){

        }


    }
}
