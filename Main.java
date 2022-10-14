
/**
 * Write a description of class Main here.
 *  The start of the exection of the assignment
 *  It allows the user to enter a sequnce if characters  
 *  Then produces a sequence of packets from them with the
 *  aid of two buffers
 * 
 * @author Rasheed senior
 * @version (a version number or a date)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;
import java.util.Arrays;


public class Main extends Thread
{
 
    private static final int MAX_MESSSAGE_LENGTH = 255; // Maximum length of the message 
    private static final int MAIN_BUFFER_SIZE = 12; // Maximum size of the main and secondary buffer.
    public static final int STIME = 500; // sleep time for threads to follow outputs as they appear
    
    // Array of boolean values that each thread assigns its corresponding element to false once it has completed execution and is to die  
    public static Boolean threadStates[] = new Boolean[3]; 
    
    
    
    public static void main(String[] args)
    {
        
        Arrays.fill(threadStates, Boolean.FALSE); // fill the array with false values
        
        //array of characters that holds the message once accepted
        Mutex_Buffer message_reservoir = new Mutex_Buffer(MAX_MESSSAGE_LENGTH,"message_reservoir"); 
        
        //main buffer 
        Mutex_Buffer main_buffer = new Mutex_Buffer(MAIN_BUFFER_SIZE,"main_buffer");
        
        //second buffer
        Mutex_Buffer secondary_buffer = new Mutex_Buffer(MAIN_BUFFER_SIZE,"secondary_buffer");
               
        // accept the message from std input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
          
        String message; // string to store user input  
        

         
        System.out.println("Enter the text to be converted");
        try{
        // Reading data using readLine
         message = reader.readLine();
        }

        catch(IOException e)
        {
            message = "An error has occured";
        }
        
        //Confirms the message to the user
        System.out.println(message); 
        
        
        char[] msg_arr = message.toCharArray(); // converts message to character array
        
        /** loop moves the message to the reservoir so that it can be read 
         * from one character at a time until completion.
         */
        
        if(msg_arr.length <= MAX_MESSSAGE_LENGTH)
        {
           for(int i =0; i<= msg_arr.length - 1; i++)
            {
                message_reservoir.Put(msg_arr[i]);
                
            } 
            System.out.println("message_reservoir has full message");
            
        }
        else
        {
           for(int i =1; i<= MAX_MESSSAGE_LENGTH; i++)
            {
                message_reservoir.Put(msg_arr[i]);
            } 
            System.out.println("message_reservoir full");
        }
        
        
        //start the three threads and pass a reference the reqiured resource as parameters
         Thread1 t1 = new Thread1(message_reservoir,main_buffer); // passes a reference to the message and the main buffer
         Thread2 t2 = new Thread2(main_buffer,secondary_buffer,message_reservoir); // pass reference to the reservoir, the main and secondary message 
         Thread3 t3 = new Thread3(secondary_buffer); // pass a reference to the second buffer

         t1.start();
         t2.start();
         t3.start();
        
         /**
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
         
            **/
         
    }


}
