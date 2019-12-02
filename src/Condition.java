/**
 * Condition = column operator constant
 * ex: => name = 'omar'
 */

public class Condition {
    
    private String column;
    private String operator;
    private String constant;

    public Condition(String col, String op, String cons){
        this.column=col.replaceAll(" ", "");
        this.operator=op.replaceAll(" ", "");
        this.constant=cons.replaceAll(" ", "").replaceAll(";", "");
    }
 

    /**
     * @return the constant
     */
    public String getConstant() {
        return constant;
    }
    /**
     * @return the column
     */
    public String getColumn() {
        return column;
    }
    /**
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }

}