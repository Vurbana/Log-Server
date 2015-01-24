package com.sofia.uni.fmi.threads;



import com.sofia.uni.fmi.writter.Writter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by vankata on 23.12.14.
 */
public class RequestHandler implements Runnable{
    private Socket socket = null;
    private DateFormat dateFormat;

    public RequestHandler(Socket s){
        this.socket = s;
        dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
    public void run(){

        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
            String input=null;
            String indentifier = null;
            StringBuilder sb = new StringBuilder();
            indentifier = br.readLine();

            while((input = br.readLine())!= null) {

                Calendar calendar = Calendar.getInstance();
                sb.append(dateFormat.format(calendar.getTime()));
                sb.append(" " + "[" + indentifier + "]: ");
                sb.append(input);
                Writter.writeInLogFile(sb);


                sb.setLength(0);

            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(socket!=null){
                try{
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }
}
