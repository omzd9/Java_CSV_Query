import java.util.StringTokenizer;
import java.util.Scanner;
/*
    this class is a representation of the filepath.
    it takes import statement << import filepath>> and extract the filepath and save it.
*/

class  InputFile{
    private String filePath;

    //Constructer
    public InputFile(String imp)
    {
        //initializing the object
           setFilePath(imp);
            
    }
    
    /**
     * @param filePath the filePath to set
     * extract the filepath from the statement
     * input : import filepath 
     * result : this.filepath= filepath
     */
    public void setFilePath(String imp) {
        String str="";
        boolean condition = true;
        int i =0;
        while(condition)
        {
            if(i>imp.length()-1)
            {
                condition=false;
                
            }
            else{
                    char c= imp.charAt(i);
                    if(str.equalsIgnoreCase("import")){
                        this.filePath = imp.substring(i+1);
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