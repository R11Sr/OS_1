import java.util.concurrent.Semaphore;

/**
 * Write a description of class Buffer here.
 *  The Buffer class handles the syncronisation of the threads
 *  It uses a monitor to pause the producer threads when the  buffers are full.
 *  This is accomplished using syncronised methods, allowing to make use of the wait()
 *  and notify() functions in Java.
 *  
 *  The two semaphores that are used to acheive mutual exclusion of thread 2 and thread 3. 
 * 
 * @author Rasheed senior
 * @version 1.0.0
 */

class Mutex_Buffer {
            private char [] buffer; // collection to store characters for use  
            private int count = 0; // variable to to keep track of amount of characters in the buffer
            private int in = 0, out = 0; //pointer to keep track of where to read into the buffer and to read from
            private  int BUFFER_SIZE; // the size that the buffer is to have
            private String name; // the name of the buffer. For Identification purposes
            static Semaphore consume = new Semaphore(1); 
            static Semaphore produce = new Semaphore(1);
            
            /**
             * Constructor for objects of class Buffer
             */
            Mutex_Buffer(int sz, String name)
            {
                BUFFER_SIZE = sz;
                 buffer = new char[BUFFER_SIZE];
                this.name = name;
            }
 
            /**
             * Method to put put character into buffer
             *
             * @param  c  character to put store in the collection
             * @return    none
             */
            public synchronized void Put(char c) {
                
                 //while the buffer is full the producer thread goes
                 //into busy wait
                 while(count == buffer.length) 
                 {
                     System.out.println("Buffer Full...");
                      try { wait(); }
                      catch (InterruptedException e) { } 
                      finally { } 
                 } 
                 
                 // if the buffer is not full, use the semaphores 
                 //to achieve mutual exclusion for this shared resource
                 try{
                     produce.acquire(); //semWait()
                     System.out.println("Producing " + c + " ...");
                     buffer[in] = c; // store the character in the buffer
                     in = (in + 1) % buffer.length; // move the location of the point to the next available space
                     count++; // increase the count for the items stored in the buffer.
                     System.out.println("count: " + count);
                         
                    }
                 catch(InterruptedException ex){}
                 finally{
                    produce.release(); //semSignal()
                    }

                 notify(); // sends a message to a waiting thread that it can proceed
                 
            }
            
            /**
             * This method returns a character from the buffer if
             * is not empty
             *        
             * @return  character from the buffer
             */
            public synchronized char Get() {
                 
                 //while the buffer is empty the consumer thread goes
                 //into busy wait
                 while (count == 0) 
                 {
                     System.out.println("Buffer " + this.name + " is Empty...");
                      try { wait();
                            
                        }
                      catch (InterruptedException e) { } 
                      finally { } 
                 } 
                 char c;
                 
                   // if the buffer is not empty, use the semaphores 
                   //to achieve mutual exclusion for this shared resource
                   try{
                        consume.acquire(); //semWait()
                        c = buffer[out]; // reads from the buffer
                        out = (out + 1) % buffer.length; // moves the location of the out pointer to the last element
                        count--; // reduces the count of the amount of characters in buffer
                        System.out.println("Consuming " + c + " ..."); 
                        System.out.println("count: " + count);
                        
                    }
                    catch(InterruptedException ex){
                    //returns lower case 'e' if and error occurs
                    c = 'e';
                    }
                    finally{
                    
                    consume.release(); //semSignal()
                }              
                
                 notify(); // sends a message to a waiting thread that it can proceed
                 return c;
            }
            
            
            /**
             * This method returns a boolean value
             * true if the buffer is empty 
             * false if the buffer has contents
             *        
             * @return  state of the buffer, if it is full or not
             */
            
            public boolean empty(){
                if(count <= 0) {return true;}
                return false;
            }
            
            /**
             * This method returns size of the buffer 
             *        
             * @return  buffer size
             */
            public int length(){
                return buffer.length;
            }
            
            /**
             * This method returns amount of characters in the buffer
             *        
             * @return  amount of buffer elements
             */
              public int amount(){
                return count;
            }
            
            
            /**
             * This method returns String representation of the buffer
             *        
             * @return  String of buffer
             */
            public  String toString(){
                String str = "";
                for(char c : buffer){
                    str = str + c;
                }
                
                return str;
            }
      }