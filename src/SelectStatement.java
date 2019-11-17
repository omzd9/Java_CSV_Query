import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;
import java.nio.MappedByteBuffer;;
import java.nio.channels.FileChannel;
import java.io.IOException;


class SelectStatement extends Statement{
   
    String[] columns;

    // constructor to initialize my select statement
    public SelectStatement(String Statement){
        // calling Statement Constructor
        super(Statement);
        // setting the columns variable state
        setColumns();
        
    }
  
    /**
     * set columns variable state
     */
    public void setColumns() {
        String temp = this.extractSubString(statement,"select");
        temp.split("from");
        String token =  temp.split("from")[0];
        StringTokenizer column = new StringTokenizer(token,",");
        String[] columns = new String[column.countTokens()];
        int i=0;
        while(column.hasMoreTokens())
        {
            columns[i++]=column.nextToken();
        }
        this.columns = columns;
    }
    /**
     * @return the column
     */
    public String[] getColumns() {
        return columns;
    }
    /**
     * query excute the query based in the sql statement columns and csv file Columns
     * it takes the csv file as an argument and output the result 
     */

    
    public void query(FileTraitement csv)
    {
        try{
            for (String string : columns) {
                System.out.print(string + "\t");
            }
            System.out.print("\n\n");
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
               
                char c = (char)buffer. get((int) i);
           
                if(c=='\n')
                {
                        OutPutData(new String(line),csv.getColumnNames());
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
    private void OutPutData(String line,String[] csvColumns){
        String[] data = line.split(",");
        for (String SqlColumn : columns) {
            for (int i =0; i<csvColumns.length ;i++ ) {
                if(SqlColumn.replaceAll(" ","").equals(csvColumns[i].replaceAll(" ", "")) ){
                       
                    System.out.print(data[i]+"\t");;
                }
              
            }
        }
        System.out.print("\n");
    }

}