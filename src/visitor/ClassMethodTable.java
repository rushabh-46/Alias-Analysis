package visitor;

import java.util.HashMap;
import java.util.Map.Entry;

public class ClassMethodTable {
  
  public String className;
  
  public String methodName;
  
  public HashMap <Symbol, SymbolTableEntry> table = null;

  public ClassMethodTable(String methodName, String className) {
    this(methodName);
    this.className = className;
  }

  public ClassMethodTable(String string) {
    this.methodName = string;
    this.table = new HashMap<Symbol, SymbolTableEntry> ();
    this.className = null;
  }

  public void addClassName(String className) {
    this.className = className;
  }
  
  public void print() {
    for (Entry<Symbol, SymbolTableEntry> e : table.entrySet()) {
      e.getKey().print();
    }
  }

  /**
   * Search for the type of the following object name which has this method
   * @param primaryId
   * @param classDefinitions 
   * @param methodName2
   * @return
   */
  public String search(String primaryId, String mName, HashMap<String, ClassDetails> classDefinitions) {
    // first search id in table and check out all the possible types it has
    System.out.println("Asked to search for primaryId = " + primaryId
        + " and method name = " + mName);
    for (Entry<Symbol, SymbolTableEntry> e : table.entrySet()) {
      if(e.getKey().getVarName() == primaryId) {
        // now for this type refer to the classes (hash type) and check if they have this method
        String thisType = e.getKey().getTypeName();
        System.out.println("\t  thisType = " + thisType + " and varname = " + e.getKey().getVarName());
        if(classDefinitions.get(thisType).methods.containsKey(mName)) {
          System.out.println("Yes! Found the method " + mName + " in class " + thisType);
          return thisType;
        }
      }
    }
    System.out.println("Couldn't find the method " + mName);
    return null;
  }
}
