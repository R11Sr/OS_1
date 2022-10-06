

class Buffer {
            private char [] buffer;
            private int count = 0, in = 0, out = 0;
            private  int BUFFER_SIZE;
            Buffer(int sz)
            {
                BUFFER_SIZE = sz;
                 buffer = new char[BUFFER_SIZE];
            }
 
            public synchronized void Put(char c) {
                 while(count == buffer.length) 
                 {
                      try { wait(); }
                      catch (InterruptedException e) { } 
                      finally { } 
                 } 
                 System.out.println("Producing " + c + " ...");
                 buffer[in] = c; 
                 in = (in + 1) % buffer.length; 
                 count++; 
                 System.out.println("HHEELLPP ME" + count);
                 notify(); 
                 
            }
    
            public synchronized char Get() {
                
                 while (count == 0) 
                 {
                      try { wait(); }
                      catch (InterruptedException e) { } 
                      finally { } 
                 } 
                 char c = buffer[out]; 
                 out = (out + 1) % buffer.length;
                 count--;
                 System.out.println("Consuming " + c + " ..."); 
                 notify(); 
                 return c;
            }
            
            public  String toString(){
                String str = "";
                for(char c : buffer){
                    str = str + c;
                }
                
                return str;
            }
      }