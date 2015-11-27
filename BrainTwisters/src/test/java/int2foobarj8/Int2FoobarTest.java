package int2foobarj8;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Int2FoobarTest {

    @Test
    public void testInt2Foobar_1() {

        List<String> foobars = Int2FooBar_1.ints2foobars(0, 30);

        assertEquals(30 / 3 + 1, foobars.stream().filter(s -> s.matches(".*foo.*")).count());
        assertEquals(30 / 5 + 1, foobars.stream().filter(s -> s.matches(".*bar.*")).count());
        assertEquals(30 / (3*5) + 1, foobars.stream().filter(s -> s.matches(".*foobar.*")).count());
    }

    @Test
    public void testInt2Foobar_2() {

        List<String> foobars = int2foobarj8.Int2FooBar_2.ints2foobars(0, 30);

        assertEquals(30 / 3 + 1, foobars.stream().filter(s -> s.matches(".*foo.*")).count());
        assertEquals(30 / 5 + 1, foobars.stream().filter(s -> s.matches(".*bar.*")).count());
        assertEquals(30 / (3*5) + 1, foobars.stream().filter(s -> s.matches(".*foobar.*")).count());
    }

    @Test
    public void testInt2Foobar_3() {

        List<IntStringPair> foobars = Int2FooBar_3.ints2foobars(0, 30);

        assertEquals(30 / 3 + 1, foobars.stream().filter(s -> s._2().indexOf("foo") >= 0).count());
        assertEquals(30 / 5 + 1, foobars.stream().filter(s -> s._2().indexOf("bar") >= 0).count());
        assertEquals(30 / (3*5) + 1, foobars.stream().filter(s -> s._2().indexOf("foobar") >= 0).count());
    }
}
