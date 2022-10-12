
/**
 * Write a description of class Packet here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Packet
{
    private final int MAX_PACKET_SIZE = 5;
    private static int packet_count = -1;
    private int charCount = 0;
    char content[] = new char[MAX_PACKET_SIZE];

    /**
     * Constructor for objects of class Packet
     */
    public Packet()
    {
        packet_count++;
    }


    public void Add(char ch){
        content[charCount] = ch;
        charCount++;
    }
    
    public boolean full(){
    
        if(charCount >= MAX_PACKET_SIZE){return true;}
        return false;
    }
    
    public int quantity(){
    return charCount;
    }
    
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
