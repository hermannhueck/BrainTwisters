/*
http://www.warski.org/blog/2014/02/using-scala-traits-as-modules-or-the-thin-cake-pattern/

Using Scala traits as modules, or the “Thin Cake” Pattern

Impl 2: Thin Cake Pattern
-------------------------

What if the object graph, and at the same time the main class, becomes large? The answer is simple: we have to break it into pieces, which will be the “modules”. Each module is a Scala trait, and contains some part of the object graph.

For example:

trait CropModule {
     lazy val wheatField = new WheatField()
     lazy val mill = new Mill(wheatField)
}

trait LivestockModule {
     lazy val cowPasture = new CowPasture()
     lazy val diaryFarm = new DiaryFarm(cowPasture)
}

The main object then becomes a composition of traits. This is exactly what also happens in the Cake Pattern.
However here we are using only one element of it, hence the “Thin Cake” Pattern name.

If you have ever used Google Guice, you may see a similarity: trait-modules directly correspond to Guice modules.
However, here we gain the additional type-safety and compile-time checking that dependency requirements for all classes are met.

Of course, the module trait can contain more than just new object instantiations, however you have to be cautious not to put too much logic in there
– at some point you probably need to extract a class. Typical code that also goes into modules is e.g. new actor creation code and setting up caches.

*/

package cake.warski.bakery

import cake.warski.bakery.models.Models._

object ThinCakePattern extends App with CropModule with LivestockModule {

  val me = new Person

  lazy val bakery = new Bakery(mill, diaryFarm)

  // using the object graph
  val cake = bakery.bakeCake
  me.eat(cake)
}
