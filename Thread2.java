
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
    
    //default constructor
       public Thread2(Buffer message_buffer)
    {
        this.buff = message_buffer;
    }

    public void run() 
    {
        System.out.println("Thread 2 Started");
        char test = buff.Get();
    }
}
