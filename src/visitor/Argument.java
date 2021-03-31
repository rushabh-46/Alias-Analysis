package visitor;

/**
 * Argument class for each function definition.
 * It has variable name at caller site.
 */
public class Argument {

  /** name of the argument at caller site */
  private String siteName;

  /** the name of the type of the argument */
  private String typeName;

  /** COnstructor for Argument */
  Argument(String name, String type) {
    this.siteName = name;
    this.typeName = type;
  }

  /** Getter for method name */
  public String getArgumentName() {
    return this.siteName;
  }

  /** Getter for type name */
  public String getTypeName() {
    return this.typeName;
  }
}
