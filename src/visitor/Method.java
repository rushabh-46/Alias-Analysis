package visitor;

import java.util.ArrayList;

public class Method {

  /** Name of the method */
  private String methodName;

  /** return type of the mathod */
  private String returnType;

  /** return identifier */
  private String returnName;

  /** The formal parameters of the method */
  /** Should it include class object 'this' ??????????? */
  public ArrayList<Argument> arguments;

  /** The fields within the methods */
  public ArrayList<Field> fields;

  /** number of arguments including 'this' */
  private int numOfArguments;

  /** Constructor for Method class */
  Method(String name, String type) {
    this.methodName = name;
    this.returnType = type;
    this.numOfArguments = 1;
    this.arguments = new ArrayList<Argument>();
    this.arguments.add(new Argument("this", type));
    this.fields = new ArrayList<Field>();
  }

  /** add argument for the method */
  public void addArgument(String name, String type) {
    this.numOfArguments++;
    this.arguments.add(new Argument(name, type));
  }

  /** add argument for the method */
  public void addArgument(Argument a) {
    this.numOfArguments++;
    this.arguments.add(a);
  }

  /** Add locals (field) for the method*/
  public void addField(String name, String type) {
    this.fields.add(new Field(name, type));
  }

  /** set return id name */
  public void setReturn(String name) {
    this.returnName = name;
  }

  /** Helper function to print method details */
  public void print() {
    System.out.println(
      "Method details: " +
      this.methodName +
      " with return type " +
      this.returnType +
      " and name " +
      this.returnName
    );
    System.out.println("\t# arguments = " + this.numOfArguments);
    for (Argument a : this.arguments) {
      System.out.println(
        "\t" + a.getArgumentName() + " at site of type " + a.getTypeName()
      );
    }
    System.out.println("\t# locals = " + this.fields.size());
    for (Field f : this.fields) {
      System.out.print("\t");
      f.print();
    }
  }
}
