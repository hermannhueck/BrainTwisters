package int2foobar

object Int2FooBar_2 extends App {

  ints2foobars(0, 30).foreach(println(_))

  def ints2foobars(from: Int, to: Int): Seq[String] =
    Range.inclusive(from, to).map(int2foobar(_)).filter(_.isDefined).map(_.get)

  def int2foobar(i: Int): Option[String] =
    if (i % 3 == 0 && i % 5 == 0)
      Option(i + ": foobar")
    else if (i % 3 == 0)
      Option(i + ": foo")
    else if (i % 5 == 0)
      Option(i + ": bar")
    else
      None
}
