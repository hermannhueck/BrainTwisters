package typeclasses

import typeclasses.model.Account

object SortedAccounts extends App {

  val accounts = Seq(Account("Hugo", 1000.0), Account("Mathilde", 5000.0), Account("Mathilde", 4000.0), Account("Anton", 2000.0))

  println
  println("sortWith by holder: " + accounts.sortWith(_.holder < _.holder))
  println("sortWith by balance: " + accounts.sortWith(_.balance < _.balance))
  println

  println("sorted by default ordering (holder, balance): " + accounts.sorted)

  import Account._
  implicit val orderingByHolder = new Ordering[Account] {
    override def compare(x: Account, y: Account): Int = compareByHolder(x, y)
  }
  println("sorted by holder: " + accounts.sorted)

  println("sorted by balance: " + accounts.sorted(Account.compareByBalance))
  println
}
