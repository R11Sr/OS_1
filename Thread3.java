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
    private Mutex_Buffer secondary_buffer;
    private Packet pack;
    
    //default constructor
    public Thread3(Mutex_Buffer sec_buffer)
    {
        //this.permit_con = permit;
       this.secondary_buffer = sec_buffer;
    }

    public void run()
    {
        pack = new Packet();
        while(true){
            
                System.out.println("secondary_buffer contains:  " + secondary_buffer.amount()); 
                if(!secondary_buffer.empty()){
                     char mv = secondary_buffer.Get();
               
                    if(!pack.full()){
                        pack.Add(mv);
                        System.out.println(pack);
                    }
                    else{
                    //System.out.println(pack);
                    pack = new Packet(); // recreate packet
                    pack.Add(mv);
                    }
                    
                
                    try{Thread.currentThread().sleep(Main.STIME);}
                    catch(Exception e){e.printStackTrace();}
                }
                else{
                    System.out.println(pack);
                }
                
                
                try{Thread.currentThread().sleep(Main.STIME);}
                    catch(Exception e){e.printStackTrace();}
                
                if(Main.threadStates[1] == true && secondary_buffer.empty())
                {
                    return;
                }

        }

    }
}
