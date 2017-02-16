package int2foobar

import org.scalatest._

class Int2FoobarSpec extends FlatSpec with Matchers {

  "Int2FooBar_2" should "11 x strings containing \"foo\", 7 x strings containing \"bar\", 3 x strings containing \"foobar\"" in {

    val foobars = Int2FooBar_2.ints2foobars(0, 30)
    foobars.count(_.contains("foo")) should be(30 / 3 + 1)
    foobars.count(_.contains("bar")) should be(30 / 5 + 1)
    foobars.count(_.contains("foobar")) should be(30 / (3*5) + 1)
  }

  "Int2FooBar_3" should "11 x strings containing \"foo\", 7 x strings containing \"bar\", 3 x strings containing \"foobar\"" in {

    val foobars = Int2FooBar_3.ints2foobars(0, 30)
    foobars.count(_._2.contains("foo")) should be(30 / 3 + 1)
    foobars.count(_._2.contains("bar")) should be(30 / 5 + 1)
    foobars.count(_._2.contains("foobar")) should be(30 / (3*5) + 1)
  }
}
