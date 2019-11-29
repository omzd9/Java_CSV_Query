import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;
import java.nio.channels.FileChannel;
import java.nio.MappedByteBuffer;


class FileTraitement{
    private String name; // file name
    private long fileSize; // file size
    private int line1Size; // it holds the size of the first line to avoid parsing it many times
    private String[] columnNames; //  column names within the csv file
    private RandomAccessFile file; // the csv file


    //constructor
    FileTraitement(String name){

            this.name=name;
            // after having the file name we can set all the state's fields
            setFile();
            setFileSize();
            setLine1Size();
            setColumnNames();
            

    }
    // closing connections before the object die
    protected void finalize() throws Throwable 
    { 
        this.file.getChannel().close();
        this.file.close();
    } 
            //getters
    /**
     * @return the name
     */
    public String getName() {
        return name;
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
     * @return the line1Sizeize
     */
    public int getLine1Size() {
        return line1Size;
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
 
                    //setters
    /**
     * this function takes the data from the first line and transform it to a string table and put it un colimnNames state variable
     */
    public void setColumnNames() {
        try{
            MappedByteBuffer buffer = file.getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, getFileSize()).load();
            buffer.clear();
            byte[] data= new byte[line1Size];
            buffer.get(data,0,line1Size-2);
            String str = new String(data);
            StringTokenizer tookens = new StringTokenizer(str,",");
            String[] columns = new String[tookens.countTokens()];
            int i=0;
            while(tookens.hasMoreTokens())
            {
                columns[i]=tookens.nextToken().replace(" ", "");
                i++;
                
            }
            String tmp =   columns[columns.length-1];
            String tmp2 =   columns[0];

            columns[columns.length-1]= tmp.substring(0, tmp.length() - 2); // problem of the last column with two espace
            
            //columns[0]= tmp2.substring(0, tmp2.length() - 2); // problem of the last column with two espace
            columnNames = columns;
            
        }catch(Exception e){
            System.err.println(e);
        }

        }
    /**
     * function for setting the file size

     */
    /**
        set the first line size
     */
    private void setLine1Size() {
        try{
            MappedByteBuffer buffer = file.getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, getFileSize()).load();
            buffer.clear();
            boolean condition=true;
            int i=0;
            while(condition) 
                {
                    char c = (char)buffer.get();
                    if(c=='\n')
                    {
                        condition=false;
                    }
                    else{
                        i++;
                    }
                
                } 
            this.line1Size=i+1;
            }catch(IOException e){
                System.err.println(e);
            }
    }
    /**
     * setting the file size
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
     * modify the csv file which will trigger all state function to update
     */
    public void setName(String name) {
        this.name = name;
        setFile();
        setFileSize();
        setLine1Size();
        setColumnNames();
    }
    
  
    /**
     * open the csv file Randomly 
     */
    private void setFile(){
        try{
        this.file = new RandomAccessFile(this.name, "r");
        }catch(IOException e){
                System.err.println(e);
            }
    }
    
    

}