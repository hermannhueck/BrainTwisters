package int2foobarj8;

public class Pair<T1, T2> {

    private T1 el1;
    private T2 el2;

    Pair(T1 _1, T2 _2) {
        el1 = _1;
        el2 = _2;
    }

    public T1 _1() {
        return el1;
    }

    public T2 _2() {
        return el2;
    }
}
