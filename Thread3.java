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
        
    }
}
