//
//Generated by JTB 1.3.2
//

package visitor;

import java.util.*;
import java.util.Map.Entry;
import syntaxtree.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJDepthFirstSymbolTable<R, A> implements GJVisitor<R, A> {

  // this gets set in Main itself. So no worries.
  public HashMap<String, ClassDetails> classDefinitions = null;

  // store the class fields and class methods separately

  // global classs fields
  public HashMap<String, ClassFieldTable> globalClassFieldTable = null;

  // global class methods - will also contain overriden methods added later
  public HashMap<String, HashMap<String, ClassMethodTable>> globalClassMethodTable =
    null;

  // current class field table
  private ClassFieldTable currentClassFieldTable = null;

  // current class method table
  private ClassMethodTable currentClassMethodTable = null;

  // current method map
  private HashMap<String, ClassMethodTable> currentClassMethodMap = null;

  // private type name
  private String varTypeName = null;

  // current class name
  private String currentClassName = null;

  public R visit(NodeList n, A argu) {
    R _ret = null;
    int _count = 0;
    for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
      e.nextElement().accept(this, argu);
      _count++;
    }
    return _ret;
  }

  public R visit(NodeListOptional n, A argu) {
    if (n.present()) {
      R _ret = null;
      int _count = 0;
      for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
        e.nextElement().accept(this, argu);
        _count++;
      }
      return _ret;
    } else return null;
  }

  public R visit(NodeOptional n, A argu) {
    if (n.present()) return n.node.accept(this, argu); else return null;
  }

  public R visit(NodeSequence n, A argu) {
    R _ret = null;
    int _count = 0;
    for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
      e.nextElement().accept(this, argu);
      _count++;
    }
    return _ret;
  }

  public R visit(NodeToken n, A argu) {
    return null;
  }

  //
  // User-generated visitor methods below
  //

  /**
   * f0 -> MainClass()
   * f1 -> ( TypeDeclaration() )*
   * f2 -> <EOF>
   */
  public R visit(Goal n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "class"
   * f1 -> Identifier()
   * f2 -> "{"
   * f3 -> "public"
   * f4 -> "static"
   * f5 -> "void"
   * f6 -> "main"
   * f7 -> "("
   * f8 -> "String"
   * f9 -> "["
   * f10 -> "]"
   * f11 -> Identifier()
   * f12 -> ")"
   * f13 -> "{"
   * f14 -> ( VarDeclaration() )*
   * f15 -> ( QStatement() )*
   * f16 -> "}"
   * f17 -> "}"
   */
  public R visit(MainClass n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu); // n.f1.f0.toString()

    currentClassName = n.f1.f0.toString();
    ClassFieldTable fTable = new ClassFieldTable(n.f1.f0.toString());
    // no fields in this class
    globalClassFieldTable.put(n.f1.f0.toString(), fTable);

    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);
    n.f6.accept(this, argu);
    n.f7.accept(this, argu);
    n.f8.accept(this, argu);
    n.f9.accept(this, argu);
    n.f10.accept(this, argu);
    n.f11.accept(this, argu);
    n.f12.accept(this, argu);
    n.f13.accept(this, argu);

    ClassMethodTable mTable = new ClassMethodTable("main", currentClassName);

    currentClassMethodTable = mTable;

    n.f14.accept(this, argu);
    n.f15.accept(this, argu);
    n.f16.accept(this, argu);
    n.f17.accept(this, argu);

    HashMap<String, ClassMethodTable> mMap = new HashMap<String, ClassMethodTable>();
    mMap.put("main", mTable);
    globalClassMethodTable.put(n.f1.f0.toString(), mMap);
    currentClassMethodTable = null;
    return _ret;
  }

  /**
   * f0 -> ClassDeclaration()
   *       | ClassExtendsDeclaration()
   */
  public R visit(TypeDeclaration n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "class"
   * f1 -> Identifier()
   * f2 -> "{"
   * f3 -> ( VarDeclaration() )*
   * f4 -> ( MethodDeclaration() )*
   * f5 -> "}"
   */
  public R visit(ClassDeclaration n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);

    currentClassName = n.f1.f0.toString();
    ClassFieldTable fTable = new ClassFieldTable(n.f1.f0.toString());
    currentClassFieldTable = fTable;

    n.f3.accept(this, argu);

    globalClassFieldTable.put(n.f1.f0.toString(), fTable);
    currentClassFieldTable = null;
    HashMap<String, ClassMethodTable> mMap = new HashMap<String, ClassMethodTable>();
    currentClassMethodMap = mMap;

    n.f4.accept(this, argu);

    globalClassMethodTable.put(n.f1.f0.toString(), mMap);
    currentClassMethodMap = null;

    n.f5.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "class"
   * f1 -> Identifier()
   * f2 -> "extends"
   * f3 -> Identifier()
   * f4 -> "{"
   * f5 -> ( VarDeclaration() )*
   * f6 -> ( MethodDeclaration() )*
   * f7 -> "}"
   */
  public R visit(ClassExtendsDeclaration n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);

    currentClassName = n.f1.f0.toString();
    ClassFieldTable fTable = new ClassFieldTable(n.f1.f0.toString());
    currentClassFieldTable = fTable;

    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);

    globalClassFieldTable.put(n.f1.f0.toString(), fTable);
    currentClassFieldTable = null;
    HashMap<String, ClassMethodTable> mMap = new HashMap<String, ClassMethodTable>();
    currentClassMethodMap = mMap;

    n.f6.accept(this, argu);

    globalClassMethodTable.put(n.f1.f0.toString(), mMap);
    currentClassMethodMap = null;

    n.f7.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Type()
   * f1 -> Identifier()
   * f2 -> ";"
   */
  public R visit(VarDeclaration n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);

    String typeName = varTypeName;

    n.f1.accept(this, argu);
    n.f2.accept(this, argu);

    if (currentClassMethodTable != null) {
      currentClassMethodTable.table.put(
        new Symbol(n.f1.f0.toString(), typeName),
        new SymbolTableEntry(n.f1.f0.toString(), typeName)
      );
    } else if (currentClassFieldTable != null) {
      currentClassFieldTable.table.put(
        new Symbol(n.f1.f0.toString(), typeName),
        new SymbolTableEntry(n.f1.f0.toString(), typeName)
      );
    } else {
      System.out.println(
        "ERROR: Which var Declaration bro? id = " + n.f1.f0.toString()
      );
    }

    //    Symbol s = new Symbol(n.f1.f0.toString(), )
    //    currentClassTable.put(new Symbol(), new SymbolTableEntry());

    return _ret;
  }

  /**
   * f0 -> "public"
   * f1 -> Type()
   * f2 -> Identifier()
   * f3 -> "("
   * f4 -> ( FormalParameterList() )?
   * f5 -> ")"
   * f6 -> "{"
   * f7 -> ( VarDeclaration() )*
   * f8 -> ( QStatement() )*
   * f9 -> "return"
   * f10 -> Identifier()
   * f11 -> ";"
   * f12 -> "}"
   */
  public R visit(MethodDeclaration n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu); // method name

    ClassMethodTable mTable = new ClassMethodTable(
      n.f2.f0.toString(),
      currentClassName
    );
    currentClassMethodTable = mTable;

    // add the class fields by same reference (duplicating)
    addClassFieldsInMethod();

    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);
    n.f6.accept(this, argu);
    n.f7.accept(this, argu);
    n.f8.accept(this, argu);
    n.f9.accept(this, argu);
    n.f10.accept(this, argu);
    n.f11.accept(this, argu);
    n.f12.accept(this, argu);

    // Adding method table in class method map
    currentClassMethodMap.put(n.f2.f0.toString(), mTable);

    currentClassMethodTable = null;

    return _ret;
  }

  private void addClassFieldsInMethod() {
    // GLOBAL FIELD TABLE DOES NOT CONTAIN inherited fields!!!!!!!!!!!!!!!!
    //    currentClassFieldTable = globalClassFieldTable.get(currentClassName);
    //    if (currentClassFieldTable == null) {
    //      System.out.println("Failed to get className from global Field table");
    //      return;
    //    }
    //    for (Entry<Symbol, SymbolTableEntry> e : currentClassFieldTable.table.entrySet()) {
    //      currentClassMethodTable.table.put(e.getKey(), e.getValue());
    //    }

    // Using previously created classHierarchy to access all the inherited and current fields
    ClassDetails curCls = classDefinitions.get(currentClassName);
    if (curCls == null) {
      System.out.println(
        "Failed to get class" + currentClassName + " from classDefinitions"
      );
      return;
    }
    curCls.fields.forEach(
      (name, field) -> {
        System.out.println(
          "ADDDDDDDDDDDDDDDDDDDDDDDDDDDing for class " +
          currentClassMethodTable.className +
          " the field " +
          name +
          " in method " +
          currentClassMethodTable.methodName
        );
        currentClassMethodTable.table.put(
          new Symbol(name, field.getTypeName()),
          new SymbolTableEntry(name, field.getTypeName())
        );
      }
    );
  }

  /**
   * f0 -> FormalParameter()
   * f1 -> ( FormalParameterRest() )*
   */
  public R visit(FormalParameterList n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Type()
   * f1 -> Identifier()
   */
  public R visit(FormalParameter n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    String typeName = varTypeName;
    n.f1.accept(this, argu);
    currentClassMethodTable.table.put(
      new Symbol(n.f1.f0.toString(), typeName),
      new SymbolTableEntry(n.f1.f0.toString(), typeName)
    );
    return _ret;
  }

  /**
   * f0 -> ","
   * f1 -> FormalParameter()
   */
  public R visit(FormalParameterRest n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> ArrayType()
   *       | BooleanType()
   *       | IntegerType()
   *       | Identifier()
   */
  public R visit(Type n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "int"
   * f1 -> "["
   * f2 -> "]"
   */
  public R visit(ArrayType n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    varTypeName = "int[]";
    return _ret;
  }

  /**
   * f0 -> "boolean"
   */
  public R visit(BooleanType n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    varTypeName = "Boolean";
    return _ret;
  }

  /**
   * f0 -> "int"
   */
  public R visit(IntegerType n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    varTypeName = "int";
    return _ret;
  }

  /**
   * f0 -> ( Query() )*
   * f1 -> Statement()
   */
  public R visit(QStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> <SCOMMENT1>
   * f1 -> Identifier()
   * f2 -> "alias?"
   * f3 -> Identifier()
   * f4 -> <SCOMMENT2>
   */
  public R visit(Query n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Block()
   *       | AssignmentStatement()
   *       | ArrayAssignmentStatement()
   *       | FieldAssignmentStatement()
   *       | IfStatement()
   *       | WhileStatement()
   *       | PrintStatement()
   */
  public R visit(Statement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "{"
   * f1 -> ( QStatement() )*
   * f2 -> "}"
   */
  public R visit(Block n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "="
   * f2 -> Expression()
   * f3 -> ";"
   */
  public R visit(AssignmentStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "["
   * f2 -> Identifier()
   * f3 -> "]"
   * f4 -> "="
   * f5 -> Identifier()
   * f6 -> ";"
   */
  public R visit(ArrayAssignmentStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);
    n.f6.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "."
   * f2 -> Identifier()
   * f3 -> "="
   * f4 -> Identifier()
   * f5 -> ";"
   */
  public R visit(FieldAssignmentStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "if"
   * f1 -> "("
   * f2 -> Identifier()
   * f3 -> ")"
   * f4 -> Statement()
   * f5 -> "else"
   * f6 -> Statement()
   */
  public R visit(IfStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);
    n.f6.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "while"
   * f1 -> "("
   * f2 -> Identifier()
   * f3 -> ")"
   * f4 -> Statement()
   */
  public R visit(WhileStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "System.out.println"
   * f1 -> "("
   * f2 -> Identifier()
   * f3 -> ")"
   * f4 -> ";"
   */
  public R visit(PrintStatement n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> AndExpression()
   *       | CompareExpression()
   *       | PlusExpression()
   *       | MinusExpression()
   *       | TimesExpression()
   *       | ArrayLookup()
   *       | ArrayLength()
   *       | MessageSend()
   *       | FieldRead()
   *       | PrimaryExpression()
   */
  public R visit(Expression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "&&"
   * f2 -> Identifier()
   */
  public R visit(AndExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "<"
   * f2 -> Identifier()
   */
  public R visit(CompareExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "+"
   * f2 -> Identifier()
   */
  public R visit(PlusExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "-"
   * f2 -> Identifier()
   */
  public R visit(MinusExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "*"
   * f2 -> Identifier()
   */
  public R visit(TimesExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "["
   * f2 -> Identifier()
   * f3 -> "]"
   */
  public R visit(ArrayLookup n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "."
   * f2 -> "length"
   */
  public R visit(ArrayLength n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> "."
   * f2 -> Identifier()
   */
  public R visit(FieldRead n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> PrimaryExpression()
   * f1 -> "."
   * f2 -> Identifier()
   * f3 -> "("
   * f4 -> ( ArgList() )?
   * f5 -> ")"
   */
  public R visit(MessageSend n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    String primaryId = varTypeName;
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    String methodName = n.f2.f0.toString();
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    n.f5.accept(this, argu);

    // Adding bottomToTop methods.
    // Assuming <Primary Expression> is <Identifier>
    // how to search for this token? There could be multiple with same name
    String typeName = currentClassMethodTable.search(
      primaryId,
      methodName,
      classDefinitions
    );
    // now do bottomToTop on this typeName for the method
    if (typeName != null) {
      classDefinitions.get(typeName).subToSuper(methodName);
    }
    return _ret;
  }

  /**
   * f0 -> Identifier()
   * f1 -> ( ArgRest() )*
   */
  public R visit(ArgList n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> ","
   * f1 -> Identifier()
   */
  public R visit(ArgRest n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> IntegerLiteral()
   *       | TrueLiteral()
   *       | FalseLiteral()
   *       | Identifier()
   *       | ThisExpression()
   *       | ArrayAllocationExpression()
   *       | AllocationExpression()
   *       | NotExpression()
   */
  public R visit(PrimaryExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> <INTEGER_LITERAL>
   */
  public R visit(IntegerLiteral n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "true"
   */
  public R visit(TrueLiteral n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "false"
   */
  public R visit(FalseLiteral n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> <IDENTIFIER>
   */
  public R visit(Identifier n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    varTypeName = n.f0.toString();
    return _ret;
  }

  /**
   * f0 -> "this"
   */
  public R visit(ThisExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "new"
   * f1 -> "int"
   * f2 -> "["
   * f3 -> Identifier()
   * f4 -> "]"
   */
  public R visit(ArrayAllocationExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    n.f4.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "new"
   * f1 -> Identifier()
   * f2 -> "("
   * f3 -> ")"
   */
  public R visit(AllocationExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    n.f2.accept(this, argu);
    n.f3.accept(this, argu);
    return _ret;
  }

  /**
   * f0 -> "!"
   * f1 -> Identifier()
   */
  public R visit(NotExpression n, A argu) {
    R _ret = null;
    n.f0.accept(this, argu);
    n.f1.accept(this, argu);
    return _ret;
  }

  public void print() {
    for (Entry<String, ClassFieldTable> fMap : globalClassFieldTable.entrySet()) {
      System.out.println("Class field details for " + fMap.getKey());
      fMap.getValue().print();
      System.out.println();
    }

    for (Entry<String, HashMap<String, ClassMethodTable>> mMap : globalClassMethodTable.entrySet()) {
      System.out.println("Class method details for " + mMap.getKey());

      for (Entry<String, ClassMethodTable> mTable : mMap
        .getValue()
        .entrySet()) {
        System.out.println("Method field details for " + mTable.getKey());
        mTable.getValue().print();
        System.out.println();
      }
      System.out.println();
    }
  }
}
