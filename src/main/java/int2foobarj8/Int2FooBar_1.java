package int2foobarj8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Int2FooBar_1 {

    public static void main(String[] args) {

        ints2foobars(0, 30).forEach(System.out::println);
    }

    public static List<String> ints2foobars(int from, int to) {

        return IntStream.rangeClosed(from, to)
                    .boxed()
                    .map(Int2FooBar_1::int2foobar)
                    .filter(s -> s != null)
                    .collect(Collectors.toList());
    }

    public static String int2foobar(int i) {
        if (i % 3 == 0 && i % 5 == 0)
            return i + ": foobar";
        if (i % 3 == 0)
            return i + ": foo";
        if (i % 5 == 0)
            return i + ": bar";
        return null;
    }
}
