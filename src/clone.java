class Main {

  public static void main(String[] args) {
    A a;
    B b;
    int t;
    a = new A();
    b = new B();
    t = b.foo();
  }
}

class A {

  A f;

  public int getT() {
    int x;
    x = 2;
    return x;
  }
}

class B extends A {

  public int foo() {
    int x;
    f = new A();
    x = 2;
    return x;
  }
}
