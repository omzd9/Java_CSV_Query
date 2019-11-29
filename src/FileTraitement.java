
import java.util.StringTokenizer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.FileSystems;

class FileTraitement{
    private String pattern; // file name 
    private String[] columnNames; //  column names within the csv file
    private Path path; // lalalalal



    //constructor
    FileTraitement(String pattern){

            this.pattern=pattern;
            // after having the file pattern we can set all the state's fields
            //setFile();
            setPath();
            setColumnNames();
            

    }
    /**
     * @param path the path to set
     */
    private void setPath() {
        this.path = FileSystems.getDefault().getPath(pattern);
    }
    /**
     * @return the path
     */
    public Path getPath() {
        return path;
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
   
  
 
                    //setters
    /**
     * this function takes the data from the first line and transform it to a string table and put it un colimnNames state variable
     */
    public void setColumnNames() {
        try{
            
            String firstLine= Files.lines(path).findFirst().get(); // get first line
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
     * @param pattern the pattern to set
     * * modify the csv file which will trigger all state function to update
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
        setPath();
        setColumnNames();
    }
    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }
  

}