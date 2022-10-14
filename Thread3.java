import java.util.concurrent.Semaphore;

/**
 * Thread 3 is responsible for taking character from the secondary buffer
 * one at a time and to create packets
 *
 * @author Rasheed R. Senior
 * @version 1.0.0
 */
public class Thread3 extends Thread
{
    private Mutex_Buffer secondary_buffer; // refernce to the second buffer
    private Packet pack; // the packet class
    
    //default constructor
    public Thread3(Mutex_Buffer sec_buffer)
    {
       this.secondary_buffer = sec_buffer;
    }

    public void run()
    {
        pack = new Packet(); // creates a new packet upon starting
        while(true){
            
                System.out.println("secondary_buffer contains:  " + secondary_buffer.amount()); 
                System.out.println("secondary_buffer has the following:  " + secondary_buffer); 
                /**
                   If the secondary buffer is not empty it will read character from it and place it in the packet
                   created. If the packet is full it will print it. 
                   if the buffer is empty it will print the packet.  
                   ***/
                
                if(!secondary_buffer.empty()){
                     char mv = secondary_buffer.Get();
               
                    //add to a packet if it has space
                    if(!pack.full()){
                        pack.Add(mv);
                        System.out.println(pack); //debugging
                    }
                    else{
                    // print full packet
                    System.out.println(pack);
                    pack = new Packet(); // create new packet
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
                
                 /**
                    Terminating condition of the loop
                    thread 3 dies if thread 2 is dead and the secondary buffer is empty
                    */
                if(Main.threadStates[1] == true && secondary_buffer.empty())
                {
                    return; //thread dies
                } 

        }

    }
}
