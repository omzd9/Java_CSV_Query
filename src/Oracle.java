import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectableChannel;
import java.util.StringTokenizer;


import java.util.Scanner;
public class Oracle{
    
 
    public static void main(String[] args) throws Exception 
    {
        
        FileTraitement csv = new FileTraitement(new ImportStatement("import ../document.csv").getFilePath());
        SelectStatement sql = new SelectStatement("select ID,Name from document;");
        
        sql.query(csv);
        //System.out.println(csv.getBuffer().toString());
        
        
        
    }
}

