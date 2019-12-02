
import java.util.StringTokenizer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.FileSystems;
/*
* this class represent a csv file in the schema
*/

class FileTraitement {
    
    private String fileName; // file name 
    private String[] columnNames; //  column names within the csv file
    private Path path; 



    //constructor
    FileTraitement(String filename){    
            // after having the file pattern we can set all the state's fields
            //setFile();
            this.fileName=filename;
            setPath();
            setColumnNames();
            

    }
   
    private void setPath() {
        this.path = FileSystems.getDefault().getPath(fileName);
    }
    /**
     * @return the path
     */
    public Path getPath() {
        return path;
    }
   
    /**
     * @return the columnNames
     */
    public String[] getColumnNames() {
        if(columnNames==null)
            {
                setColumnNames();
            }
        return columnNames;
    }
   
  
 
    /**
     * this function takes the first line and transform it to a string table and put it un columnNames state variable
     */
    private void setColumnNames() {
        try{
            
            String firstLine= Files.lines(path).findFirst().get(); // get the first csv file line
            StringTokenizer tookens = new StringTokenizer(firstLine,",");
            String[] columns = new String[tookens.countTokens()];
            int i=0;
            while(tookens.hasMoreTokens())
            {
                columns[i]=tookens.nextToken().replace(" ", "");
                i++;
                
            }
           
            columnNames = columns;
            
        }catch(Exception e){
            System.err.println(e);
        }

    }
    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String file) {
        
        this.fileName= file;
        setPath();
        setColumnNames();
    }
    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
  

}