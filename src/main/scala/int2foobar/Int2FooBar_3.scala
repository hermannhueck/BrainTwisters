package int2foobar

object Int2FooBar_3 extends App {

  ints2foobars(0, 30).foreach(pair => println(pair._1 + ": " + pair._1))

  def ints2foobars(from: Int, to: Int): Seq[(Int, String)] =
    Range.inclusive(from, to).map(int2foobar(_)).filter(_.isDefined).map(_.get)

  def int2foobar(i: Int): Option[(Int, String)] =
    if (i % 3 == 0 && i % 5 == 0)
      Option((i, "foobar"))
    else if (i % 3 == 0)
      Option((i, "foo"))
    else if (i % 5 == 0)
      Option((i, "bar"))
    else
      None
}
