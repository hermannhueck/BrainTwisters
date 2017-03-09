package funcreturn;

@FunctionalInterface
interface T2R<T, R> {
    R process(T x);
}

interface IntToInt extends T2R<Integer, Integer> {}

public class FuncReturn {

    public static void main(String[] args) {

        IntToInt f = x -> x * x;

        int result = compute(2, f);
        System.out.println("result = " + result);

        result = compute(5, computation("*"));
        System.out.println("result = " + result);
    }

    private static int compute(int x, IntToInt i2i) {
        return i2i.process(x);
    }

    // a function returning another function.
    // the return type of this function is the functional interface type.
    private static IntToInt computation(String op) {

        if (op == null)
            throw new IllegalArgumentException();
        op = op.trim();

        switch (op) {
            case "+":
                return x -> x + 2;
            case "-":
                return x -> x - 2;
            case "*":
                return x -> x * 2;
            case "/":
                return x -> x / 2;
            case "%":
                return x -> x % 2;
        }

        throw new IllegalArgumentException();
    }
}
