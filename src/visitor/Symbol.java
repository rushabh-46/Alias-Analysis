package visitor;

public class Symbol {
  
  /** name of the variable */
  public String varName;
  
  /** type of the variable -> int, boolean, class type */
  private String typeName;
  // also the reference to class?
  
  /** constructor */
  Symbol(String var, String type) {
    this.varName = var;
    this.typeName = type;
  }
  
  /** getter for name of the symbol */
  public String getVarName() {
    return this.varName;
  }
  
  /** getter for type of symbol */
  public String getTypeName() {
    return this.typeName;
  }

  public void print() {
    System.out.println("var: " + varName + " of type: " + typeName);    
  }
  
}
