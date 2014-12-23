package com.sofia.uni.fmi.logger;

import java.io.*;
import java.util.Formatter;

/**
 * Created by vankata on 23.12.14.
 */
public class Writter {
    private static Writter writter = null;


    private Writter(){

    }
    public static Writter getInstance(){
        if (writter == null){
            writter = new Writter();
        }

            return writter;

    }


    public  void writeInLogFile(StringBuilder sb){
        try(Formatter formatFile = new Formatter(new BufferedWriter(new FileWriter("log.txt", true)))){

            formatFile.format("%s\n", sb.toString());

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
