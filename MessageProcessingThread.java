import java.util.concurrent.Semaphore;


/**
 * Write a description of class MessageProcessingThread here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MessageProcessingThread extends Thread
{
    // instance variables - replace the example below with your own
    private int x;
    private Semaphore prod_permit;
    private Semaphore  con_permit;
    private String threadName;

    /**
     * Constructor for objects of class MessageProcessingThread
     */
    public MessageProcessingThread(Semaphore permit,String threadName)
    {
        super(threadName);
        this.permit = permit;  
        this.threadName = threadName;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
    
    @Override
    public void run() {
        if(this.getName().equals("Thread2")){
             System.out.println("Running " + threadName); 
            while(true){
                char mv = Main.main_buffer.Get();
                 try
                 {
                     permit.acquire(); 
                     System.out.println("PERMIT @ T2: ");
                      //System.out.println("Thread 2 Moving: " + mv);
                        Main.secondary_buffer.Put(Character.toUpperCase(mv));
                    System.out.println("secondary_buffer after Put: " + Main.secondary_buffer);
                
                    try{Thread.currentThread().sleep(Main.STIME);}
                    catch(Exception e){e.printStackTrace();}
                     
                 }
                 catch (InterruptedException ie)
                 {
                     ie.printStackTrace();
                 }
                 finally{
                    
                    } 
                    permit.release(); // equiv of semSignal()
                    System.out.println("PERMIT Release @ T2: ");
                    try
                    {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException ie)
                    {
                        ie.printStackTrace();
                    }
            } 
        }
        
        else{
            System.out.println("Running " + threadName);
             
            Packet pack = new Packet();
            try
                 {
                    
                    System.out.println("secondary_buffer contains:  " + Main.secondary_buffer.amount()); 
                    if(true){
                         permit.acquire();
                         System.out.println("PERMIT @ T3: " );
                         char mv = Main.secondary_buffer.Get();
                        System.out.println("secondary_buffer after Put: " + Main.secondary_buffer);
                        if(!pack.full()){
                            pack.Add(mv);
                        }
                        else{
                        System.out.println(pack);
                        pack = new Packet(); // recreate packet
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
                    permit.release();
                    System.out.println("PERMIT release @ T3: ");
                    }
        }
    }
}
