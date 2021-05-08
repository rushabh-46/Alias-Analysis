class Main4 {

  public static void main(String[] args) {
    MN a;
    Child1 b1;
    Child1 b2;
    Subchild c1;
    Subchild c2;
    int y;

    a = new MN();
    b1 = new Child1();
    b2 = new Child1();
    c1 = new Subchild();
    c2 = new Subchild();

    y = b1.set(a);
    y = b2.set(a);

    /* b1 alias? b2 */

    y = c1.set(b1);
    y = c2.set(b2);

    b2 = c1.getChild1();

    /* c1 alias? c2 */
    y = 4;
    System.out.println(y);
  }
}

class MN {}

class Child1 extends MN {

  MN x;

  public int set(MN a) {
    int t;

    x = a;

    t = 0;
    return t;
  }
}

class Subchild extends Child1 {

  Child1 f;

  public Child1 getChild1() {
    return f;
  }

  public int set(Child1 b) {
    int t;
    Subchild thisC;

    f = b;
    thisC = this;
    t = b.set(thisC);

    t = 0;
    return t;
  }
}
