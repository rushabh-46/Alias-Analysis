 class MultiAllocate {
    public static void main(String[] args) {
        t a;
        t b;
        int v;
        v=1;
        a = new t();
        b = a.n();
        a = b.n();
        a = a.n();
        /* a alias? b */
        System.out.println(v);
    }
}

class t {
    public t n() {
        t b1;
        t c1;
        b1 = new s();
        /* b1 alias? c1 */
        c1 = this;
        return b1; 
    }
}

class s extends t {
    public t n() { 
        t b2;
        t c2;
        c2 = this;
        /* b2 alias? c2 */
        b2 = new s();
        return b2; 
    }
}
