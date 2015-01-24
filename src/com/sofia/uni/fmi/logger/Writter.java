package com.sofia.uni.fmi.logger;

import java.io.*;
import java.util.Formatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by vankata on 23.12.14.
 */
public class Writter {

    private static Lock lock = new ReentrantLock();

    public static void writeInLogFile(StringBuilder sb){
        lock.lock();
        try(Formatter formatFile = new Formatter(new BufferedWriter(new FileWriter("log.txt", true)))){

            formatFile.format("%s\n", sb.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
