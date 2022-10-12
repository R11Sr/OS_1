import java.util.concurrent.Semaphore;


class Mutex_Buffer {
            private char [] buffer;
            private int count = 0, in = 0, out = 0;
            private  int BUFFER_SIZE;
            private String name;
            static Semaphore consume = new Semaphore(1); 
            static Semaphore produce = new Semaphore(1);
            
            //constructor
            Mutex_Buffer(int sz, String name)
            {
                BUFFER_SIZE = sz;
                 buffer = new char[BUFFER_SIZE];
                this.name = name;
            }
 
            public synchronized void Put(char c) {
                 while(count == buffer.length) 
                 {
                     System.out.println("Buffer Full...");
                      try { wait(); }
                      catch (InterruptedException e) { } 
                      finally { } 
                 } 
                 
                 try{
                     produce.acquire();
                     System.out.println("Producing " + c + " ...");
                     buffer[in] = c; 
                     in = (in + 1) % buffer.length; 
                     count++; 
                     System.out.println("count: " + count);
                         
                    }
                 catch(InterruptedException ex){}
                 finally{
                    produce.release();
                    }

                 notify(); 
                 
            }
            
    
            public synchronized char Get() {

                 while (count == 0) 
                 {
                     System.out.println("Buffer " + this.name + " is Empty...");
                      try { wait();
                            
                        }
                      catch (InterruptedException e) { } 
                      finally { } 
                 } 
                 char c;
                 
                   try{
                        consume.acquire();
                        c = buffer[out];
                        out = (out + 1) % buffer.length;
                        count--;
                        System.out.println("Consuming " + c + " ..."); 
                        System.out.println("count: " + count);
                        
                    }
                    catch(InterruptedException ex){
                    //returns lower case 'e' if and error occurs
                    c = 'e';
                    }
                    finally{
                    
                    consume.release();
                }              
                
                 notify(); 
                 return c;
            }
            
            public boolean empty(){
                if(count <= 0) {return true;}
                return false;
            }
            
            public int length(){
                return buffer.length;
            }
            
              public int amount(){
                return count;
            }
            
            public  String toString(){
                String str = "";
                for(char c : buffer){
                    str = str + c;
                }
                
                return str;
            }
      }