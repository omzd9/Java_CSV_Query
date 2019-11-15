import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.StringTokenizer;


import java.util.Scanner;
public class Oracle{
    
 
    public static void main(String[] args) throws Exception 
    {
        
        
        FileTraitement myFile = new FileTraitement("document.csv");
        System.out.println(myFile.getLine(3));
        
    }
}

