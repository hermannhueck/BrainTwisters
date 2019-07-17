package int2foobarj8;

public class IntStringPair extends Pair<Integer, String> {

    IntStringPair(Integer _1, String _2) {
        super(_1, _2);
    }

    @Override
    public String toString() {
        return _1() + ": " + _2();
    }
}
