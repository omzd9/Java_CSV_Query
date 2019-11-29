import java.util.StringTokenizer;

/**
 * Predicat
 */
public class Predicat {

    private String predicat;
    private Condition[] conditions;
    private String connector;

    public Predicat(String pr){
        this.predicat = pr ;
        setConditions();
        setConnector();
    }



    /**
     * @param connector the connector to set
     */
    private void setConnector() {
        if(predicat.contains(" AND ") || predicat.contains(" and ") )
        {
            this.connector = "AND";
        }
        else if(predicat.contains(" OR ") || predicat.contains(" or ") )
        {
            this.connector = "OR";
        }
    }
    /**
     * @param condition the condition to set
     */
    private void setConditions() {
       setConnector();
       if(this.connector == null)
       {
           conditions = new Condition[1]; 
           StringTokenizer str = new StringTokenizer(predicat," ");
           String col,op,cons;
           col=str.nextToken(); op =str.nextToken(); cons= str.nextToken();
           conditions[0] = new Condition(col, op, cons);
       }else{

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

}