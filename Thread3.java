import java.util.concurrent.Semaphore;

/**
 * Write a description of class Thread3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Thread3 extends Thread
{
    private Semaphore permit_con;
    private Buffer secondary_buffer;
    private Packet pack;
    
    //default constructor
    public Thread3(Buffer sec_buffer,Semaphore permit)
    {
        this.permit_con = permit;
        this.secondary_buffer = sec_buffer;
    }

    public void run()
    {
        pack = new Packet();
        while(true){
                try
             {
                
                System.out.println("secondary_buffer contains:  " + secondary_buffer.amount()); 
                if(!secondary_buffer.empty()){
                     permit_con.acquire();
                     System.out.println("PERMIT @ T3: " );
                     char mv = secondary_buffer.Get();
               
                    if(!pack.full()){
                        pack.Add(mv);
                        System.out.println(pack);
                    }
                    else{
                    System.out.println(pack);
                    pack = new Packet(); // recreate packet
                    pack.Add(mv);
                    }
                    
                
                    try{Thread.currentThread().sleep(Main.STIME);}
                    catch(Exception e){e.printStackTrace();}
                }

             }
             catch (InterruptedException ie)
             {
                 ie.printStackTrace();
             }
             finally{
                permit_con.release();
                System.out.println("PERMIT release @ T3: ");
                }
        }

    }
}
