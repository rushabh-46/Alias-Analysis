class Main5 {
    public static void main(String[] args) {
        PQR a;
        Subclass b1;
        Subclass b2;
        Child c1;
        Child c2;
        int y;
        int count;
        Boolean bool;
        Boolean temp;

        a = new PQR();
        b1 = new Subclass();
        b2 = new Subclass();
        c1 = new Child();
        c2 = new Child();

        y = b1.set(a);
        y = b2.set(a);

        y = c1.modify(b2);

        count = 10;
        temp = true;

        while (temp) {

            y = c1.modify(b1);
            y = c2.modify(b2);

            y = 0;
            bool = count < y;
            bool = !bool;
            if (bool) {
                temp = false;
            } else {

            }
            y = 1;
            count = count - y;
        }

        y = c2.modify(b1);

        /* b1 alias? b2 */

        y = c1.set(b1);
        y = c2.set(b2);

        /* c1 alias? c2 */
        y = 5;
        System.out.println(y);
    }
}

class PQR {

}

class Subclass extends PQR {
    PQR x;

    public int set(PQR a) {
        int t;

        x = a;

        t = 0;
        return t;
    }
}

class Child extends Subclass {
    Subclass f;

    public Subclass getSubclass() {
        return f;
    }

    public int set(Subclass b1) {
        int t;

        f = b1;

        t = 0;
        return t;
    }

    public int modify(Subclass b) {
        int t;
        Child temp;

        f = b;
        temp = this;
        t = b.set(temp);

        t = 0;
        return t;
    }
}