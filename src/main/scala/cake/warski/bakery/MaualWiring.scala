/*
http://www.warski.org/blog/2014/02/using-scala-traits-as-modules-or-the-thin-cake-pattern/

Using Scala traits as modules, or the “Thin Cake” Pattern

Impl 1: Manual Wiring
---------------------

I would like to describe a pure-Scala approach to modularity that we are successfully using in a couple of our Scala projects.

But let’s start with how we do Dependency Injection (see also my other blogs). Each class can have dependencies in the form of constructor parameters, e.g.:

class WheatField
class Mill(wheatField: wheatField)
class CowPasture
class DiaryFarm(cowPasture: CowPasture)
class Bakery(mill: Mill, dairyFarm: DairyFarm)
At the “end of the world”, there is a main class which runs the application and where the whole object graph is created:

The wiring can be done manually, or e.g. using MacWire.

Note that we can do scoping using Scala constructs: a lazy val corresponds to a singleton object (in the constructed object graph),
a def to a dependent-scoped object (a new instance will be created for each usage).
 */

package cake.warski.bakery

import cake.warski.bakery.models.Models._

object MaualWiring extends App {

  val me = new Person

  // creating the object graph
  lazy val wheatField = new WheatField()
  lazy val mill = new Mill(wheatField)
  lazy val cowPasture = new CowPasture()
  lazy val diaryFarm = new DiaryFarm(cowPasture)
  lazy val bakery = new Bakery(mill, diaryFarm)

  // using the object graph
  val cake = bakery.bakeCake
  me.eat(cake)
}
