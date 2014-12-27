package com.sofia.uni.fmi.threads;



import com.sofia.uni.fmi.logger.Writter;
import com.sun.org.apache.xpath.internal.SourceTree;

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
public class RequestHandler extends Thread{
    private Socket socket = null;
    private DateFormat dateFormat;
    private Writter writter;
    public RequestHandler(Socket s){
        this.socket = s;

        this.writter = Writter.getInstance();
        dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    }
    public void run(){

        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
            String input=null;
            String indentifier = null;
            StringBuilder sb = new StringBuilder();
            indentifier = br.readLine();

            while((input = br.readLine())!= null) {
                System.out.println("Read from client:" +input);
                Calendar calendar = Calendar.getInstance();
                sb.append(dateFormat.format(calendar.getTime()));
                sb.append(" " + "[" + indentifier + "]: ");
                sb.append(input);
                synchronized (writter){
                  writter.writeInLogFile(sb);
                }
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
