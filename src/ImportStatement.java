import java.util.StringTokenizer;
import java.util.Scanner;
/*
    this class is a representation of the CSV filepath.
    it takes import statement << import filepath>> and extract the filepath and save it.
*/

class  ImportStatement extends Statement{
    private String filePath; // ex : import filepath;

    //Constructer
    public ImportStatement(String importStatement)
    {
        //initializing the object
        // call the Statament constructor
            super(importStatement);

            setFilePath();
            
    }
    
    /**
     * @param filePath the filePath to set
     * extract the filepath from the statement
     * input : import filepath 
     * result : this.filepath= filepath
     */
    private void setFilePath() {
        String str = this.extractSubString(statement, "import");
        this.filePath = str.replace(" ", "").replace(";", "");
    }
    /**
     * @return the filePath
     * output : return the filepath
     */
    public String getFilePath() {
        return filePath;
    }
}