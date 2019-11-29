/**
 * Condition 
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
     * @param column the column to set
     */
    public void setColumn(String column) {
        this.column = column.replaceAll(" ", "");
    }
    /**
     * @param constant the constant to set
     */
    public void setConstant(String constant) {
        this.constant = constant.replaceAll(" ", "");
    }
    /**
     * @param operator the operator to set
     */
    public void setOperator(String operator) {
        this.operator = operator.replaceAll(" ", "").replaceAll(";", "");
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