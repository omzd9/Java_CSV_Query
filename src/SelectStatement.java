import java.util.StringTokenizer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * this class represent the select statement
 */
class SelectStatement extends Statement{
   
    String[] columns; // sql select column
    String fromFilePath; // from which file we gonna select
    Predicat predicat; // the select statement predicat

    // constructor to initialize my select statement
    public SelectStatement(String Statement){
        // calling Statement Constructor
        super(Statement); //this.statement = Statement
        // setting the columns variable state
        setColumns();
        setPredicat();
        setFromFile();
        
    }
    /**
     * @param fromFile the fromFile to set
     */
    private void setFromFile() {
        setPredicat();
        String[] str= statement.split(" FROM ");
        if(predicat==null){
            this.fromFilePath = str[1].replace(";","").replace(" ", "") ;
        }
        else{
            this.fromFilePath = str[1].split(" WHERE ")[0].replace(" ", "") ;
        }
    }

    /**
     * set columns variable state
     */
    public void setColumns() {
        String temp = this.extractSubString(statement,"SELECT");
        //temp.split("from");
        String token =  temp.split("FROM")[0];
        StringTokenizer column = new StringTokenizer(token,",");
        String[] columns = new String[column.countTokens()];
        int i=0;
        while(column.hasMoreTokens())
        {
            columns[i++]=column.nextToken().replaceAll(" ","") ;
        }
        this.columns = columns;
    }
  
    
    private  void setPredicat() {
        
        String[] tookens = statement.split(" WHERE ");
        if(tookens.length<2)
        {
            predicat=null;
        }
        else{
            predicat = new Predicat(tookens[1]);
        }
    }
    /**
     * @return the column
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * @return the predicat
     */
    public Predicat getPredicat() {
        return predicat;
    }
  
      /**
     * @param condition : the condition which we want too evaluate
     * @param data : a single line of csv file
     * @param csv : the fileTraitement where data has been tooken
     * this function return true if data is conform to condition clause (data = single csv line)
     */
    private boolean conditionTrue(Condition condition,String[] data,FileTraitement csv){
        String[] csvColumns = csv.getColumnNames();
        for (int j =0; j<csvColumns.length ;j++ ) {
            if(condition.getColumn().equals(csvColumns[j]))
            {
                switch (condition.getOperator() )
                {

                    case "=" :
                            if(condition.getConstant().contains("'")) 
                            // if constant contain the character ' so it's string type
                            {
                                if(data[j].equals(condition.getConstant().replaceAll("'", "")))
                                    {
                                        return true;
                                    }
                                    
                            }
                            else{ // type of data is int
                                if(Integer.parseInt(data[j]) == Integer.parseInt(condition.getConstant()))
                                    {
                                        return true;
                                    }
                            }
                    
                        break;
                    case "!=":
                                if(condition.getConstant().contains("'")) 
                                // if constant contain the character ' so it's string type
                                {
                                    if(!data[j].equals(condition.getConstant().replaceAll("'", "")))
                                        {
                                            return true;
                                        }
                                }
                                else{ // type of data is int
                                    if(Integer.parseInt(data[j]) != Integer.parseInt(condition.getConstant()))
                                        {
                                            return true;
                                        }
                                }
                        break;

                    case ">" : //it's surely int
                        if(Integer.parseInt(data[j])> Integer.parseInt(condition.getConstant()))
                            {
                                return true;
                            }
                        break;

                    case "<" : //it's surely int
                        if(Integer.parseInt(data[j])< Integer.parseInt(condition.getConstant()))
                        {
                            return true;
                        }
                        break;

                        }
            }
        }
        return false;
    }
      /**@param csv input file 
         * the main function that excute the query
         * it excute the query based in the sql statement columns and csv file Columns
         * it takes the csv file as an argument and output the result  
     */
    public void query(FileTraitement csv)
    {
        String header="";
        try{
            for (String string : columns) {
                header=header + string + "\t";
            }
            System.out.println(header);
            String firstLine= Files.lines(csv.getPath()).findFirst().get(); 
            String[] csvColumns = csv.getColumnNames();
            Files.lines(csv.getPath()).forEach(line->{
                String[] data = line.split(",");
                Boolean conditionsVerified2 =false; // second condition is not veridief yet
                Boolean conditionsVerified =false; // first condition is not veridief yet
                if(!line.equals(firstLine))
                {
                    if(predicat==null) // if there's any conditions
                    {
                        
                        outPutData(line,csvColumns);
                    }
                    else{ // predicat exist
                        conditionsVerified = conditionTrue(this.predicat.getConditions()[0], data, csv);// vrifie first condition
                        if(this.predicat.getConnector()!=null) // if we have two conditions
                           
                            {
                                conditionsVerified2 = conditionTrue(this.predicat.getConditions()[1], data, csv);//verifie second condition
                            }
                               
                        } //prdicat exist
                if(predicat!=null)// if predicat = null no need to print because already done
                        {
                            if(this.predicat.getConnector()==null)
                                {
                                        if(conditionsVerified){outPutData(line, csvColumns);}
                                }
                                else{
                                    if(this.predicat.getConnector()=="AND" || this.predicat.getConnector()=="and" )
                                    {
                                        if(conditionsVerified && conditionsVerified2){outPutData(line, csvColumns);}
                                    }
                                    if(this.predicat.getConnector()=="OR" || this.predicat.getConnector()=="or" )
                                    {
                                        if(conditionsVerified || conditionsVerified2){outPutData(line, csvColumns);}
                                    }

                                }
                        }
                
                }//line > fisrt line
            }// end of line
               
            
            );

        }catch(IOException e){
            System.err.println(e);
        }
        
    }
    /**
     * it takes single csv file line and only print the columns specified in the select query
     * @param line single line of csv file
     * @param csvColumns   the cvs columns name
     */
    private void outPutData(String line,String[] csvColumns){
      

        String[] data = line.split(","); String lineOutPut="";
        //data[0]= data[0].substring(1);
        for (String queryColumn : this.columns) {
            for (int i =0; i<csvColumns.length ;i++ ) {
                if(queryColumn.equals(csvColumns[i]) ){
                           
                           lineOutPut = lineOutPut+ data[i]+ '\t' ;
                                   
                }
              
            }
        }
        System.out.println(lineOutPut);
        
    }
    /**
     * @return the fromFile
     */
    public String getFromFilePath() {
        return fromFilePath;
    }

}