
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
import java.util.Arrays;


public class Main extends Thread
{
    private static final int MAX_AVAILABLE = 1;
    private static final int MESSAGE_BUFFER_SIZE = 255;
    private static final int MAIN_BUFFER_SIZE = 12;
    public static final int STIME = 500;
    public static Boolean threadStates[] = new Boolean[3];
    
    
    
    public static void main(String[] args)
    {
        
        Arrays.fill(threadStates, Boolean.FALSE);
        
        
        //Semaphore permit_prod = new Semaphore(MAX_AVAILABLE, true);
       //  Semaphore permit_con = new Semaphore(0, true);
        
        Mutex_Buffer main_buffer = new Mutex_Buffer(MAIN_BUFFER_SIZE,"main_buffer");
        Mutex_Buffer message_buffer = new Mutex_Buffer(MESSAGE_BUFFER_SIZE,"message_buffer");
        Mutex_Buffer secondary_buffer = new Mutex_Buffer(MAIN_BUFFER_SIZE,"secondary_buffer");
               
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
            System.out.println("message_buffer has full message");
            
        }
        else
        {
           for(int i =1; i<= MESSAGE_BUFFER_SIZE; i++)
            {
                message_buffer.Put(msg_arr[i]);
            } 
            System.out.println("message_buffer full");
        }
        
         Thread1 t1 = new Thread1(message_buffer,main_buffer);
         Thread2 t2 = new Thread2(main_buffer,secondary_buffer,message_buffer);
         Thread3 t3 = new Thread3(secondary_buffer);

         t1.start();
         t2.start();
         t3.start();
         
        /**
         try
         {
             t2.join();
             t3.join();
         }
         catch (InterruptedException ie)
         {
             ie.printStackTrace();
         }
         **/
         
        
         
         while(t1.isAlive()){
                System.out.println("Thread 1 waiting....");
                try{
         t1.sleep(500);   
            } 
            catch(Exception e)
            {}
            }
            
         while(t2.isAlive()){
                System.out.println("Thread 2 waiting....");
                try{
         t2.sleep(500);   
            } 
            catch(Exception e)
            {}
            }
            
                 
         while(t3.isAlive()){
                System.out.println("Thread 3 waiting....");
                try{
         t3.sleep(500);   
            } 
            catch(Exception e)
            {}
            }
         
       // main_buffer = t1.copy
         
         
    }


}
