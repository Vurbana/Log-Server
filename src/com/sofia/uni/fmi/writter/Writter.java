package com.sofia.uni.fmi.writter;

import java.io.*;
import java.util.Formatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vankata on 23.12.14.
 */
public class Writter {



    public synchronized static void writeInLogFile(StringBuilder sb){
        try(Formatter formatFile = new Formatter(new BufferedWriter(new FileWriter("log.txt", true)))){
            formatFile.format("%s\n", sb.toString());
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
