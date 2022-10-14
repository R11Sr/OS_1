import java.util.concurrent.Semaphore;

/**
 * Write a description of class Thread2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Thread2 extends Thread
{

    //default constructor
    private Mutex_Buffer buff;
    private Mutex_Buffer secondary_buffer;
    private Mutex_Buffer msg_buff;
    
    //default constructor
       public Thread2(Mutex_Buffer main_buffer,Mutex_Buffer sec_buffer, Mutex_Buffer msg_buffer)
    {
        this.buff = main_buffer;
        this.secondary_buffer = sec_buffer;
        this.msg_buff = msg_buffer;
      
    }

    public void run() 
    {
        while(true){
            
            if(msg_buff.empty() && buff.empty()){
                Main.threadStates[1] = true;
                //System.out.println("/*/*/*/* THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
                return; //thread dies
            }
            
            
            char mv = buff.Get();
            //char mv = 'a';
            secondary_buffer.Put(Character.toUpperCase(mv));
            //System.out.println("secondary_buffer after Put: " + secondary_buffer);
            
            try{Thread.currentThread().sleep(Main.STIME);}
                catch(Exception e){e.printStackTrace();}
                
            /**
            if(msg_buff.empty() && buff.empty()){
            Main.threadStates[1] = true;
            System.out.println("---- THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
            return;
            }
            else{
                 System.out.println("---- THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
            }
            **/    
        }        
    }
}
