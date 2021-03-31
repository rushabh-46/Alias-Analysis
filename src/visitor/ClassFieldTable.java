package visitor;

import java.util.HashMap;
import java.util.Map.Entry;

public class ClassFieldTable {
  
  private String className;

  public HashMap <Symbol, SymbolTableEntry> table = null;
  
  public ClassFieldTable(String string) {
    this.className = string;
    this.table = new HashMap<Symbol, SymbolTableEntry> ();
  }

  public void print() {
    for (Entry<Symbol, SymbolTableEntry> e : table.entrySet()) {
      e.getKey().print();
    }
  }

}
