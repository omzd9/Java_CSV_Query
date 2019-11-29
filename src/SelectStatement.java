import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.io.IOException;


class SelectStatement extends Statement{
   
    String[] columns;
    //FileTraitement inputFile;
    Condition[] conditions;
    Predicat predicat;

    // constructor to initialize my select statement
    public SelectStatement(String Statement){
        // calling Statement Constructor
        super(Statement); //this.statement = Statement
        // setting the columns variable state
        setColumns();
        setPredicat();
        
    }
  
    /**
     * set columns variable state
     */
    public void setColumns() {
        String temp = this.extractSubString(statement,"select");
        //temp.split("from");
        String token =  temp.split("from")[0];
        StringTokenizer column = new StringTokenizer(token,",");
        String[] columns = new String[column.countTokens()];
        int i=0;
        while(column.hasMoreTokens())
        {
            columns[i++]=column.nextToken().replaceAll(" ","") ;
        }
        this.columns = columns;
    }
    /**
     * 
     */
    private  void setPredicat() {
        
        String[] tookens = statement.split(" where ");
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
     * query excute the query based in the sql statement columns and csv file Columns
     * it takes the csv file as an argument and output the result 
     * @param csv input file  
     */

    
    public void query(FileTraitement csv)
    {
        String myStr="";
        try{
            for (String string : columns) {
                myStr=myStr + string + "\t";
            }
            System.out.println(myStr);
            MappedByteBuffer buffer = csv.getFile().getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, csv.getFileSize()).load();
            
            boolean condition =true;
            long i=csv.getLine1Size();
            String line ="";
            buffer.clear();
            while(condition)
            {
                if(i>=csv.getFileSize())
                {
                    condition=false;
                    break;
                }
               
                char c = (char)buffer.get((int) i);
           
                if(c=='\n')
                {
                    if(predicat==null)
                    {
                        
                        outPutData(line,csv.getColumnNames());
                    }
                    else{
                        String[] csvColumns = csv.getColumnNames();
                        String columnPredicat= predicat.getConditions()[0].getColumn();
                        String[] data = line.split(",");
                        
                        for (int j =0; j<csvColumns.length ;j++ ) {
                            if(columnPredicat.equals(csvColumns[j]))
                            {
                                switch (this.predicat.getConditions()[0].getOperator() ){

                                    case "=" :
                                    
                                        break;
                                    case "!=":
                                        break;
    
                                    case ">" : //it's surely int
                                        if(Integer.parseInt(data[j])> Integer.parseInt(this.predicat.getConditions()[0].getConstant()))
                                            {
                                                outPutData(line, csvColumns);
                                            }
                                        break;
    
                                    case "<" : //it's surely int
                                        if(Integer.parseInt(data[j])< Integer.parseInt(this.predicat.getConditions()[0].getConstant()))
                                        {
                                            outPutData(line, csvColumns);
                                        }
                                        break;
    
                                        }
                            }
                        }
                    }
                    line="";
                       
                }
                else{
                    line = line+c;
                }
                i++;
            }
        }catch(IOException e){
            System.err.println(e);
        }
        
    }
    /**
     * it takes single csv file line and try to output only the columns specified in the sql query
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

}