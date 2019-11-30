/**
 * this class a parent class for all the  user's input  which is :
    * Select statement : SelectStatement.java 
    * Import statement : ImportStatement.java
 */

public abstract class Statement{
    String statement;
    protected Statement(String st){
        this.statement=st;
    }
    /**
     * temp : the original string
     * toTake : the string to take from the original string
     * ex : temp = import /directory/file   totake=import     return = /directory/file 
     */
    public  String extractSubString(String original,String toTake) {
        String str="";
        boolean condition = true;
        int i =0;
        while(condition)
        {
            if(i>original.length()-1)
            {
                condition=false;
                
            }
            else{
                    char c= original.charAt(i);
                    if(str.equalsIgnoreCase(toTake)){
                        return original.substring(i+1);
                        
                    }
                   
                    else if (c!=' ')
                    {
                        str = str+c;
                    }
                    i++;
                }
        }
        return null;
    }
}