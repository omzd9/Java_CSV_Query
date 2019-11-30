import java.util.StringTokenizer;

/**
 * this class represent the query predicat
 */
public class Predicat {

    private String predicat; // what comes after where 
    private Condition[] conditions; // condition list 
    private String connector; // [or,and,null]

    public Predicat(String predicat){
        this.predicat = predicat ;
        setConditions();
        setConnector();
    }



    /**
     * @param connector the connector to set
     */
    private void setConnector() {
        if(predicat.contains(" AND ") )
        {
            this.connector = "AND";
        }
        else if(predicat.contains(" and ") ){
            this.connector = "and";
        }
        else if(predicat.contains(" OR ") )
        {
            this.connector = "OR";
        }else if(predicat.contains(" or ")){
            this.connector = "or";
        }
    }
    /**
     * @param condition the condition to set
     */
    private void setConditions() {
       setConnector();
       if(this.connector == null) // if we have only one condition without connector || select column.. from document;
       {
           conditions = new Condition[1]; 
           StringTokenizer str = new StringTokenizer(predicat," ");
           String col,op,cons;
           col=str.nextToken(); op =str.nextToken(); cons= str.nextToken();
           conditions[0] = new Condition(col, op, cons);
       }else{ // if we have two conditions with connector || select .. where column1 op2 const1 AND column2 op2 const2;
           
            conditions = new Condition[2]; 
            String[] str = predicat.split(this.connector); // str contain my two conditions
            // str = ["column1 op2 const1"," column2 op2 const2"]
            String col,op,cons;
       
            for (int i=0;i<str.length;i++) {
                String[] cond = str[i].split(" ") ;// seperate my condition column,operator,const
                if(i==0)
                {
                    col=cond[0]; op =cond[1]; cons= cond[2];
                   
                }
                else{ // not tested
                    
                    col=cond[1]; op =cond[2]; cons= cond[3];
                  
                }

                conditions[i] = new Condition(col, op, cons);
                
             
            }
            
       }
    }

    /**
     * @return the condition
     */
    public Condition[] getConditions() {
        return conditions;
    }
     
    /**
     * @return the predicat
     */
    public String getPredicat() {
        return predicat;
    }
    /**
     * @return the connector
     */
    public String getConnector() {
        return connector;
    }

}