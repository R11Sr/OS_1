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
    private Buffer buff;
    private Buffer secondary_buffer;
    private Semaphore permit;
    
    //default constructor
       public Thread2(Buffer main_buffer,Buffer sec_buffer,Semaphore permit)
    {
        this.buff = main_buffer;
        this.secondary_buffer = sec_buffer;
        this.permit = permit;        
    }

    public void run() 
    {
        System.out.println("Thread 2 Started");
        while(true){
            char mv = buff.Get();
             System.out.println("buff_main after Get: " + buff);
             try
             {
                 permit.acquire();
                  System.out.println("Thread 2 Moving: " + mv);
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
                permit.release();
                }
             
           
            
        }        
    }
}
