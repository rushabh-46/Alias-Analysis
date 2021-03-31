package visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ClassDetails {

  /** Name of the class */
  public String className;

  /** name of the parent class */
  public String extendClassName;

  /** Does it have parent class definition or not */
  public Boolean doesExtend;

  public ClassDetails parentClass;

  // list of immediate classes.
  public ArrayList<ClassDetails> childrenClasses;

  // used for doing a bunch of classDetails node traversal
  public Boolean visitNode;
  
  public HashMap<String, ArrayList<String>> usefulMethodsClasses;

  /**
   * add varDeclaration as field? or method local? or method param?
   * 1 - add class field
   * 2 - add method formal param (argument)
   * 3 - add method field
   */
  public int whatToAdd;

  /** Fields of the class */
  public HashMap<String, Field> fields;

  /** Methods of the class */
  public HashMap<String, Method> methods;

  /** current method */
  public Method currentMethod;

  public ClassDetails(String name) {
    this.className = name;
    this.doesExtend = false;
    this.extendClassName = null;
    this.fields = new HashMap<String, Field>();
    this.methods = new HashMap<String, Method>();
    this.whatToAdd = 0; // for error reporting as it is invalid
    this.currentMethod = null; // for error reporting
    this.parentClass = null;
    this.childrenClasses = new ArrayList<ClassDetails>();
    this.usefulMethodsClasses = new HashMap<String, ArrayList<String>>();
  }

  public String getClassName() {
    return this.className;
  }

  public void setExtend(String name) {
    this.doesExtend = true;
    this.extendClassName = name;
  }

  public void setAdd(int i) {
    this.whatToAdd = i;
  }

  public void addField(String name, String type) {
    this.fields.put(name, new Field(name, type));
    // update symbolTable?????
    // NO updating symbol Table!!
  }

  public void addMethod(String name, String returnType) {
    Method m = new Method(name, returnType);
    this.methods.put(name, m);
    this.currentMethod = m;
  }

  public void print() {
    System.out.println("Class name: " + this.className);
    System.out.println(
      "Total fields = " +
      this.fields.size() +
      " and Total methods = " +
      this.methods.size()
    );
    for (Entry<String, Field> f : this.fields.entrySet()) {
      f.getValue().print();
    }
    for (Entry<String, Method> m : this.methods.entrySet()) {
      m.getValue().print();
    }
    if (doesExtend) {
      System.out.println("Extends the class " + extendClassName);
    }
    System.out.println("Total direct childrens = " + childrenClasses.size());
    for (ClassDetails cl : childrenClasses) {
      System.out.print(cl.className + " ");
    }
    
    if(usefulMethodsClasses.size() > 0) 
    {
      System.out.println("Printing the children invocations for useful methods:");
      for (Entry <String, ArrayList<String>> e : usefulMethodsClasses.entrySet()){
        System.out.println("\tMethod name = " + e.getKey());
        System.out.print("\t\tUseful classses are:" );
        for (String m : e.getValue()) {          
          System.out.print(m + ", ");
        }
        System.out.println();
      }
    }
    
    System.out.println("\n");
  }

  public void bringFromTop(ClassDetails temp) {
    // allocate a temporary temp and add all the fields and methods to it.
//    ClassDetails temp = new ClassDetails("temp");
//    temp.fields.putAll(fromTop.fields);
//    temp.methods.putAll(fromTop.methods);
    System.out.println("IN CLASS " + className);
    temp.fields.forEach((k,e) -> e.print());
    
    // temp is new ClassDetails object which has no reference in original main code
    // so just use it ; read/write to it
    // but don't point any main object to temp
    for (Entry <String, Field> e : temp.fields.entrySet()) {
      System.out.println("\tChecking for " + e.getKey());
      if(!fields.containsKey(e.getKey())) {
        System.out.println("\t\tNot found so adding " + e.getKey());
        fields.put(e.getKey(), e.getValue());
      }
    }
    // now add in temp
    temp.fields.putAll(fields);
    
    // same thing for methods
    for (Entry <String, Method> e : temp.methods.entrySet()) {
      if (!methods.containsKey(e.getKey())) {
        methods.put(e.getKey(), e.getValue());
      }
    }
    temp.methods.putAll(methods);
    
    // Now call this function recursively for all its children
    for (ClassDetails cl : childrenClasses) {
      cl.bringFromTop(temp);
    }
  }
  
  public void subToSuper(String methodName) {
    
    System.out.println("Asked for subToSuper on method " + methodName + " upto class " + className);
    
    ArrayList <String> tempList = new ArrayList<String> ();
    usefulMethodsClasses.put(methodName, tempList);
    
    // if the current class or super class has the
    // given method name add it in the list
    // replace this with bringFromBottom(methodName, tempList);
    if (methods.containsKey(methodName)) {
      tempList.add(className);
    }
    
    // now invoke adding into this list for children
    for (ClassDetails cl : childrenClasses) {
      cl.bringFromBottom(methodName, tempList);
    }
  }

  private void bringFromBottom(String methodName, ArrayList<String> tempList) {
    if (methods.containsKey(methodName)) {
      tempList.add(className);
    }
    
    // now invoke adding into this list for children
    for (ClassDetails cl : childrenClasses) {
      cl.bringFromBottom(methodName, tempList);
    }
  }
}
