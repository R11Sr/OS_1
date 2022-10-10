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
    private Semaphore permit_produce;
    
    //default constructor
       public Thread2(Buffer main_buffer,Buffer sec_buffer,Semaphore permit)
    {
        this.buff = main_buffer;
        this.secondary_buffer = sec_buffer;
        this.permit_produce = permit;        
    }

    public void run() 
    {
        while(true){
            char mv = buff.Get();
            
            System.out.println("PERMIT @ T2: ");
            secondary_buffer.Put(Character.toUpperCase(mv));
            System.out.println("secondary_buffer after Put: " + secondary_buffer);
            
            try{Thread.currentThread().sleep(Main.STIME);}
                catch(Exception e){e.printStackTrace();}
                
                
            System.out.println("PERMIT Release @ T2: ");
            
            if(Main.threadStates[0] == true){
            Main.threadStates[1] = true;
            return;
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
