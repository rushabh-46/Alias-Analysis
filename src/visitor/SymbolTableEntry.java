package visitor;

public class SymbolTableEntry {
  /** name of the variable */
  public String varName;
  
  /** type of the variable -> int, boolean, class type */
  private String typeName;
  // also the reference to class?
  
  /** constructor */
  SymbolTableEntry(String var, String type) {
    this.varName = var;
    this.typeName = type;
  }
  
  /** constructor */
  SymbolTableEntry(Symbol s) {
    this.varName = s.getVarName();
    this.typeName = s.getTypeName();
  }
  
  /** getter for name of the symbol */
  public String getVarName() {
    return this.varName;
  }
  
  /** getter for type of symbol */
  public String getTypeName() {
    return this.typeName;
  }
  
}
