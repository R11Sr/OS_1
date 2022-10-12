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
    private Semaphore permit_produce;
    
    //default constructor
       public Thread2(Mutex_Buffer main_buffer,Mutex_Buffer sec_buffer, Mutex_Buffer msg_buffer)
    {
        this.buff = main_buffer;
        this.secondary_buffer = sec_buffer;
        this.msg_buff = msg_buffer;
      //  this.permit_produce = permit;        
    }

    public void run() 
    {
        while(true){
            
            if(msg_buff.empty() && buff.empty()){
                Main.threadStates[1] = true;
                System.out.println("/*/*/*/* THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
                return;
            }
            
            
            char mv = buff.Get();
            
            secondary_buffer.Put(Character.toUpperCase(mv));
            System.out.println("secondary_buffer after Put: " + secondary_buffer);
            
            try{Thread.currentThread().sleep(Main.STIME);}
                catch(Exception e){e.printStackTrace();}
                
            
            if(msg_buff.empty() && buff.empty()){
            Main.threadStates[1] = true;
            System.out.println("/*/*/*/* THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
            return;
            }
            else{
                 System.out.println("/*/*/*/* THE VALUE OF  Main.threadStates[1]: " + Main.threadStates[1]);
            }
        
            

            
               /**
             try
             {
                 permit_produce.acquire(); 
                 //System.out.println("PERMIT @ T2: ");
                  //System.out.println("Thread 2 Moving: " + mv);
                    secondary_buffer.Put(Character.toUpperCase(mv));
                System.out.println("secondary_buffer after Put: " + secondary_buffer);
            
                try{Thread.currentThread().sleep(Main.STIME);}
                catch(Exception e){e.printStackTrace();}
             }
             catch (InterruptedException ie)
             {
                 ie.printStackTrace();
             }
             finally{
                permit_produce.release(); // equiv of semSignal()
                //System.out.println("PERMIT Release @ T2: ");
                }
                **/
             
           
            
        }        
    }
}
