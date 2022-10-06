
/**
 * Write a description of class Thread1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Thread1 extends Thread
{
    private String messageFraction;
    private Buffer buff_msg,buff_main;
    
    //default constructor
    public Thread1(Buffer message_buffer, Buffer main_buffer)
    {
        this.buff_msg = message_buffer;
        this.buff_main = main_buffer;
    }

    public void run() 
    {
        System.out.println("Thread 1 Started");
        char test = buff_msg.Get();
        System.out.println(test);
    }
}
