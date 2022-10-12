
/**
 * Write a description of class Thread1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Thread1 extends Thread
{
    private String messageFraction;
    private Mutex_Buffer buff_msg,buff_main;
    
    
    //default constructor
    public Thread1(Mutex_Buffer message_buffer, Mutex_Buffer main_buffer)
    {
        this.buff_msg = message_buffer;
        this.buff_main = main_buffer;
        
    }

    public void run() 
    {
        System.out.println("Thread 1 Started");
        
        while(!buff_msg.empty()){
            
            char mv = buff_msg.Get();
           
            buff_main.Put(mv);
           //System.out.println("buff_main after Put: " + buff_main);
            
            try{
                Thread.currentThread().sleep(Main.STIME);
            }
            catch(Exception e){e.printStackTrace();}
            
            //System.out.println("during t1 exe " + buff_main);
        }
        
        
        
        Main.threadStates[0] = true; 
         //System.out.println("/*/*/*/* T1 VALUE OF  Main.threadStates[0]: " + Main.threadStates[0]);
        
        return;
        
    }
}
