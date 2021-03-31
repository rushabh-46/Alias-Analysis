import java.util.HashMap;
import java.util.Map.Entry;
import syntaxtree.*;
import visitor.*;

public class Main {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public static void main(String[] args) {
    try {
      new QTACoJavaParser(System.in);

      // root is the GOAL in our grammar. It is used to parse.
      Node root = QTACoJavaParser.Goal();

      System.out.println("Parsed successfully.\n");

      /** String -> R; ClassDetails -> A */
      ClassHeirarchy<String, ClassDetails> classHeirarchy = new ClassHeirarchy<String, ClassDetails>();
      
      // adding int, int[], Boolean as classes
      // NO NEED I GUESS?
      ClassDetails tempClassDetails = new ClassDetails("int");
      classHeirarchy.classes.put("int[]", tempClassDetails);
      tempClassDetails = new ClassDetails("int[]");
      classHeirarchy.classes.put("Boolean", tempClassDetails);
      tempClassDetails = new ClassDetails("int");
      classHeirarchy.classes.put("Boolean", tempClassDetails);
      
      root.accept(classHeirarchy, null);
      System.out.println("-------- SUPER TO SUB");
      classHeirarchy.superToSub();
      System.out.println("_________FINISHED");
      // next parsing will invoke .subToSuper() as per requirement

//      for (Entry<String, ClassDetails> entry : classHeirarchy.classes.entrySet()) {
//        entry.getValue().print();
//      }

      GJDepthFirstSymbolTable symbolTableVisitor = new GJDepthFirstSymbolTable();
      symbolTableVisitor.classDefinitions = classHeirarchy.classes;
      symbolTableVisitor.globalClassFieldTable =
        new HashMap<String, ClassFieldTable>();
      symbolTableVisitor.globalClassMethodTable =
          new HashMap<String, HashMap<String, ClassMethodTable>>();
      root.accept(symbolTableVisitor, null);
      
      System.out.println("\n-------------------SYMBOL TABLE---------------------\n");
      symbolTableVisitor.print();
      System.out.println("_____________________SYMBOL TABLE OVER__________________");
      
      for (Entry<String, ClassDetails> entry : classHeirarchy.classes.entrySet()) {
        entry.getValue().print();
      }
      
      
      
      GJDepthFirst v = new GJDepthFirst();
      root.accept(v, null);
    } catch (ParseException e) {
      System.out.println(e.toString());
    }
  }
}
