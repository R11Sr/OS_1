import java.util.concurrent.Semaphore;

/**
 * Thread 2 is responsible for moving the characters one at a time
 * from the main buffer, captialises it, then places it in the second buffer
 *
 * @author Rasheed R. Senior
 * @version 1.0.0
 */
public class Thread2 extends Thread
{

    private Mutex_Buffer buff; // reference to the main buffer
    private Mutex_Buffer secondary_buffer; // reference to the second buffer
    private Mutex_Buffer msg_buff; // reference to message received
    
    //default constructor
       public Thread2(Mutex_Buffer main_buffer,Mutex_Buffer sec_buffer, Mutex_Buffer msg_buffer)
    {
        this.buff = main_buffer;
        this.secondary_buffer = sec_buffer;
        this.msg_buff = msg_buffer;
      
    }

    public void run() 
    {
        /**
         Continuous loop   
        **/
        while(true){
            
            // Thread terminates once the message is empty and the main buffer is empty
            if(msg_buff.empty() && buff.empty()){
                Main.threadStates[1] = true;
                //System.out.println("/*/*/*/* THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
                return; //thread dies
            }
            
            
            char mv = buff.Get();
            /**
               Converts the character received from the main buffer to Upper case and places it in second buffer
               **/
            secondary_buffer.Put(Character.toUpperCase(mv)); 
            //System.out.println("secondary_buffer after Put: " + secondary_buffer);
            
            try{Thread.currentThread().sleep(Main.STIME);}
                catch(Exception e){e.printStackTrace();}
                
   
        }        
    }
}
