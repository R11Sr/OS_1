
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;


public class Main extends Thread
{
    private static final int MAX_AVAILABLE = 1;
    private static final int MESSAGE_BUFFER_SIZE = 255;
    
    public static void main(String[] args)
    {
        final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
        Buffer main_buffer = new Buffer(12);
        Buffer message_buffer = new Buffer(MESSAGE_BUFFER_SIZE);
               
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
          
        String name;   
        

         
        System.out.println("Enter the text to be converted");
        try{
        // Reading data using readLine
         name = reader.readLine();
        }

        catch(IOException e)
        {
            name = "An error has occured";
        }
        // Printing the read line
        System.out.println(name); 
        
        char[] msg_arr = name.toCharArray();
        if(msg_arr.length <= MESSAGE_BUFFER_SIZE)
        {
           for(int i =0; i<= msg_arr.length - 1; i++)
            {
                message_buffer.Put(msg_arr[i]);
                
            } 
        }
        else
        {
           for(int i =1; i<= MESSAGE_BUFFER_SIZE; i++)
            {
                message_buffer.Put(msg_arr[i]);
            } 
        }
        
         Thread1 t1 = new Thread1(message_buffer,main_buffer);
         Thread2 t2 = new Thread2(main_buffer);
         //Thread3 t3 = new Thread3(main_buffer);

         t1.start();
        
          
         try{
         t1.sleep(5000);   
            } 
            catch(Exception e)
            {}
         
         t2.start();
         //t3.start();
         
       // main_buffer = t1.copy
         
         
    }


}
