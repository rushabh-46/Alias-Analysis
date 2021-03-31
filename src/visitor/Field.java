package visitor;

public class Field {

  /** name of the field */
  private String fieldName;

  /** type of the field e.g. int, bool, class A etc */
  private String typeName;

  /** Only constructor */
  Field(String fName, String type) {
    this.fieldName = fName;
    this.typeName = type;
  }

  /** Getter fieldName */
  public String getFieldName() {
    return this.fieldName;
  }

  /** Getter typeName */
  public String getTypeName() {
    return this.typeName;
  }

  public void print() {
    System.out.println(
      "\tField name: " + this.fieldName + " of type: " + this.typeName
    );
  }
}
