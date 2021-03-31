class Main2 {
    public static void main(String[] args) {
        CA a;
        CB b;
        int y;
        a = new CA();
        b = new CB();
        y = b.foo(a);
        /* a alias? b */
        y = 2;
        System.out.println(y);
    }
}

class CA {
    CA f1;
}

class CB extends CA {
    CB f2;

    public int foo(CA x) {
        int t;
        CB temp;
        t = 0;
        temp = new CB();
        x.f1 = temp;
        return t;
    }
}