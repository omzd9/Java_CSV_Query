import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.util.StringTokenizer;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;


class FileTraitement{
    private String name; // file name
    private long fileSize; // file size
    private int offsetSize; // the number of byte in a single line
    private String[] columnNames; // data column names within csv file
    private RandomAccessFile file; // the csv file
    MappedByteBuffer buffer ; //the buffer to reading the csv file


    //constructor
    FileTraitement(String name){
        this.name=name;
            // after having the file name we can set all the file state variables
            setFile();
            setFileSize();
            setBuffer();
            setOffsetSize();
            setColumnNames();

    }
            //getters
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the buffer
     */
    public MappedByteBuffer getBuffer() {
        return buffer;
    }
    /**
     * @return the file
     */
    public RandomAccessFile getFile() {
        return file;
    }
    /**
     * @return the column
     */
    public String[] getColumnNames() {
        if(columnNames==null)
            {
                setColumnNames();
            }
        return columnNames;
    }
    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }
    /**
     * @return the offsetSize
     */
    public int getOffsetSize() {
        
        return offsetSize;
    }
                    //setters
    /**
     * this function takes the data from the first line and transform it to a string table and put it un colimnNames state variable
     */
    public void setColumnNames() {
        try{
        
            byte[] line= new byte[offsetSize];
            buffer.get(line,0,offsetSize);
            String stringColumns = new String(line);
            StringTokenizer tooknizer = new StringTokenizer(stringColumns,",");
            int i=0;
            String[] columns = new String[tooknizer.countTokens()]; 
            while (tooknizer.hasMoreTokens()) 
                {columns[i++]= tooknizer.nextToken();} 
            
            columnNames = columns;
            buffer.clear();
        }catch(Exception e){
            System.err.println(e);
        }

        }
    /**
     * function for setting the file size

     */
    private void setFileSize() {
        try{

        this.fileSize = this.file.getChannel().size();
        }catch(IOException e)
        {
            System.err.println(e);
        }

       
    }
    /**
     * @param name the name to set
     * modify the csv file which will trigger all state function update
     */
    public void setName(String name) {
        this.name = name;
        setFile();
        setFileSize();
        setBuffer();
        setOffsetSize();
        setColumnNames();
    }
    /**
     *this function return a file line according to the user input it start with 1 ... n
     */
   public String getLine(int line)
   {
            boolean condition =true;
            String stringLine = "";
            int i =1;
            
            if((line-1)*getOffsetSize()+2>getFileSize())
            {
                
                return "out passed the size";

            }
            while (condition) {
                    
                char c = (char)buffer.get( (line-1)*getOffsetSize()+i);
                if(c=='\n')
                {
                        condition=false;
                }
                else{
                    stringLine=stringLine+c;
                    
                    }
                
                i++;
            }
            return stringLine;
   }
    /**
     * set the offset size which is the length of the csv single line
     */
    private void setOffsetSize() {
       
        
        boolean condition =true;
        String myString="";
        int offset=0;
        while (condition) {
            char c = (char)buffer.get();
            if(c=='\n')
            {
                    condition=false;
            }
            else{
            myString=myString+c;
            offset++;
            }
        }
        this.offsetSize = offset;
       
       
    }
    /**
     * set the RandomAccessFile instance
    */
     
    private void setFile(){
        try{
        this.file = new RandomAccessFile(this.name, "r");
        }catch(IOException e){
                System.err.println(e);
            }
    }
    /**
     * @param buffer the buffer to set
     * // set the buffer which allow me to read the data from csv file
     */
    private void setBuffer() {
        try{
        this.buffer = file.getChannel()
            .map(FileChannel.MapMode.READ_ONLY, 0, getFileSize()).load();
        }catch(IOException e){
                System.err.println(e);
        }
    }
    
   

}