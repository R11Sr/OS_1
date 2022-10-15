
/**
 * Thread 3 takes capitalised characters and add them to packets.
 * the packet then formats the characters and when printing
 * displays the amount of packets generated upon till and including the 
 * current, the amount of characters in the current packet and the characters 
 * themselves.
 *
 * @author Rasheed R. Senior
 * @version 1.0.0
 */
public class Packet
{
    private final int MAX_CHAR_IN_PACKET_SIZE = 5;
    private static int packet_count = -1;
    private int charCount = 0; // records the amount of characters currently in the packet
    char content[] = new char[MAX_CHAR_IN_PACKET_SIZE]; // array of char to store the contents of the packet

    /**
     * Constructor for objects of class Packet
     */
    public Packet()
    {
        packet_count++;
    }

    /**
    * This method adds a captialised buffer to the packet
    *        
    * @param ch  capatilized letter to add to the packet
     */
    public void Add(char ch){
        content[charCount] = ch;
        charCount++;
    }
    
    /**
    * Inquires to see if the buffer is full or not
    *        
    * @return state of the buffer, full or not
     */
    
    public boolean full(){
    
        if(charCount >= MAX_CHAR_IN_PACKET_SIZE){return true;}
        return false;
    }
    
    /**
    * Inquries of the amount of items in the buffer
    *        
    * @return amount of characters in the packet
     */
    
    public int quantity(){
    return charCount;
    }
    
    
    /**
    * This method returns the packet in the desired human readable format
    *        
    * @return the packet as s string
     */
    public String toString(){
        StringBuilder packetInfo = new StringBuilder("");
        
        if(packet_count < 10){
        packetInfo.append("0");
        packetInfo.append(packet_count);
        }
        else{packetInfo.append(packet_count);}
        
        packetInfo.append(":");
        packetInfo.append("0");
        packetInfo.append(charCount);
        packetInfo.append(":");
        packetInfo.append(new String(content));
        return packetInfo.toString();
    }
    
}
