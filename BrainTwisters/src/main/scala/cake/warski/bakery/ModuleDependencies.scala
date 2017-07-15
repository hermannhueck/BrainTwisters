/*
http://www.warski.org/blog/2014/02/using-scala-traits-as-modules-or-the-thin-cake-pattern/

Using Scala traits as modules, or the “Thin Cake” Pattern

Impl 3: Module Dependencies
---------------------------

What if our trait modules have inter-module dependencies? There are two ways we can deal with that problem.

The first is abstract members. If there’s an instance of a class that is needed in our module, we can simply define it as an abstract member of the trait-module.
This abstract member has to be then implemented in some other module with which our module gets composed in the end.
Using a consistent naming convention helps here. The fact that all abstract dependencies are defined at some point is checked by the compiler.

The second way is composition via inheritance. If we e.g. want to create a bigger module out of three smaller modules,
we can simply extend the other module-traits, and due to the way inheritance works we can use all of the objects defined there.

*/

package cake.warski.bakery

import cake.warski.bakery.models.Models._

// composition via inheritance: bakery depends on crop and livestock modules
trait BakeryModule extends CropModule with LivestockModule {
  lazy val bakery = new Bakery(mill, diaryFarm)
}

// abstract member: we need a bakery
trait CafeModule {
  lazy val espressoMachine = new EspressoMachine()
  lazy val cafe = new Cafe(bakery, espressoMachine)

  def bakery: Bakery
}

object ModuleDependencies extends App with CafeModule with BakeryModule {

  val me = new Person

  // using the object graph
  val cake = bakery.bakeCake
  me.eat(cake)

  cafe.orderCoffeeAndCroissant()
}
