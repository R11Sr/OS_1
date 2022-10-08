import java.util.concurrent.Semaphore;

/**
 * Write a description of class Thread3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Thread3 extends Thread
{
    private Semaphore permit;
    private Buffer secondary_buffer;
    
    //default constructor
    public Thread3(Buffer sec_buffer,Semaphore permit)
    {
        this.permit = permit;
        this.secondary_buffer = sec_buffer;
    }

    public void run()
    {
        Packet pack = new Packet();
        try
             {
                 permit.acquire();
                 System.out.println("PERMIT @ T3: " );
                 char mv = secondary_buffer.Get();
                System.out.println("secondary_buffer after Put: " + secondary_buffer);
                if(!pack.full()){
                    pack.Add(mv);
                }
                else{
                System.out.println(pack);
               // Packet pack = new Packet(); // recreate packet
                }
                
            
                try{Thread.currentThread().sleep(Main.STIME);}
                catch(Exception e){e.printStackTrace();}
             }
             catch (InterruptedException ie)
             {
                 ie.printStackTrace();
             }
             finally{
                permit.release();
                System.out.println("PERMIT release @ T3: ");
                }
    }
}
