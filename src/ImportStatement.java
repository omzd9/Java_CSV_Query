import java.util.StringTokenizer;
import java.util.Scanner;
/*
    this class is a representation of the CSV filepath.
    it takes import statement << import filepath>> and extract the filepath and save it.
*/

class  ImportStatement extends Statement{
    private String filePath;

    //Constructer
    public ImportStatement(String imp)
    {
        //initializing the object
        // call the Statament constructor
            super(imp);

            setFilePath();
            
    }
    
    /**
     * @param filePath the filePath to set
     * extract the filepath from the statement
     * input : import filepath 
     * result : this.filepath= filepath
     */
    private void setFilePath() {
        String str="";
        boolean condition = true;
        int i =0;
        while(condition)
        {
            if(i>statement.length()-1)
            {
                condition=false;
                
            }
            else{
                    char c= statement.charAt(i);
                    if(str.equalsIgnoreCase("import")){
                        this.filePath = statement.substring(i+1);
                        condition =false;
                    }
                   
                    else if (c!=' ')
                    {
                        str = str+c;
                    }
                    i++;
                }
        }
    }
    /**
     * @return the filePath
     * output : return the filepath
     */
    public String getFilePath() {
        return filePath;
    }
}