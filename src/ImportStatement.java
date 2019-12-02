import java.util.StringTokenizer;
import java.util.Scanner;
/*
    *this class is a representation of import statemnt
    *it takes import statement << import filepath;>> and extract the filepath 
*/

class  ImportStatement extends Statement{
    private String filePath; // ex : import filepath;

    
    public ImportStatement(String importStatement)
    {
        // call the Statament constructor
            super(importStatement);

            setFilePath();
            
    }
    
    /*
     * extract the filepath from the import statement
     * input : import filepath 
     * result : this.filepath= filepath
     */
    private void setFilePath() {
        String str = this.extractSubString(statement, "IMPORT");
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