package typeclasses.model

case class Account(holder: String, balance: Double)

object Account {

  import Ordering._

  def compareByHolder(x: Account, y: Account): Int = String.compare(x.holder, y.holder)
  def compareByBalance(x: Account, y: Account): Int = Double.compare(x.balance, y.balance)

  implicit object AccountOrdering extends Ordering[Account] {
    override def compare(x: Account, y: Account): Int = {
      val comp = compareByHolder(x, y)
      if (comp != 0)
        comp
      else
        compareByBalance(x, y)
    }
  }
}
