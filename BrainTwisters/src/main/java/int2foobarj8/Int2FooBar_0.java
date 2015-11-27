package int2foobarj8;

import java.util.stream.IntStream;

public class Int2FooBar_0 {

    public static void main(String[] args) {

        IntStream.rangeClosed(0, 30)
                .filter(i -> i % 2 != 0)
                .boxed()
                .forEach(i -> {
                    if (i % 3 == 0 || i % 5 == 0) {
                        StringBuilder result = new StringBuilder();
                        if (i % 3 == 0)
                            result.append("foo");
                        if (i % 5 == 0)
                            result.append("bar");
                        System.out.println(result.toString());
                    }
                });
    }
}
