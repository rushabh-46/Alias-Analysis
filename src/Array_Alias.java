class Array_Alias {

  public static void main(String[] arbitrary_name) {
    int[] l1;
    int[] l2;
    boolean z;
    int c;
    A a;
    A b;
    a = new A();
    b = new B();
    b = a;
    c = 10;
    l1 = new int[c];
    a.a = l1;
    b.b = l1;
    /*l1 alias? l2*/
    /*a alias? b*/
    l2 = b.a;
  }
}

class A {

  int[] a;
  int[] b;
}

class B extends A {}
