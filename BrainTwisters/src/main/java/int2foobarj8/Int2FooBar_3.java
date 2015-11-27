package int2foobarj8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Int2FooBar_3 {

    public static void main(String[] args) {

        ints2foobars(0, 30).forEach(System.out::println);
    }

    public static List<IntStringPair> ints2foobars(int from, int to) {

        return IntStream.rangeClosed(from, to)
                    .boxed()
                    .map(Int2FooBar_3::int2foobar)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
    }

    public static Optional<IntStringPair> int2foobar(int i) {
        if (i % 3 == 0 && i % 5 == 0)
            return Optional.of(new IntStringPair(i, "foobar"));
        if (i % 3 == 0)
            return Optional.of(new IntStringPair(i, "foo"));
        if (i % 5 == 0)
            return Optional.of(new IntStringPair(i, "bar"));
        return Optional.empty();
    }
}
