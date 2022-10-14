
/**
 * Thread 1 is to move the message one character at a time from the message to 
 * the main buffer
 *
 * @author Rasheed R. Senior
 * @version 1.0.0
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Thread1 extends Thread
{
    private Mutex_Buffer buff_msg; // reference to user message
    private Mutex_Buffer buff_main; // reference to main buffer
    
    
    //default constructor
    public Thread1(Mutex_Buffer message_buffer, Mutex_Buffer main_buffer)
    {
        this.buff_msg = message_buffer;
        this.buff_main = main_buffer;
        
    }

    public void run() 
    {
        System.out.println("Thread 1 Started");
        /**
           While the user message is not empty read a character at a time
           and place in the main buffer.
           **/
        
        while(!buff_msg.empty()){
            
            char mv = buff_msg.Get();
           
            buff_main.Put(mv);// put char in the main buffer
            
           //System.out.println("buff_main after Put: " + buff_main);
            
            try{
                Thread.currentThread().sleep(Main.STIME);
            }
            catch(Exception e){e.printStackTrace();}
            
        }
        
        
        
        Main.threadStates[0] = true; 
         //System.out.println("/*/*/*/* T1 VALUE OF  Main.threadStates[0]: " + Main.threadStates[0]);
        
        return; //thread dies
        
    }
}
