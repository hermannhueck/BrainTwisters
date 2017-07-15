/*
http://www.warski.org/blog/2014/02/using-scala-traits-as-modules-or-the-thin-cake-pattern/

Using Scala traits as modules, or the “Thin Cake” Pattern

Impl 4: Multilple Module Implementations
----------------------------------------

Taking this idea a bit further, in some situations we might have trait-module-interfaces and several trait-module-implementions.
The interface would contain only abstract members, and the implementations would wire the appropriate classes.
If other modules depend only on the trait-module-interface, when we do the final composition we can use any implementation.

This isn’t perfect, however. The implementation must be known statically, when writing the code
– we cannot dynamically decide which implementations we want to use.
If we want to dynamically choose an implementation for only one trait-interface, that’s not a problem – we can use a simple “if”.
But every additional combination causes an exponential increase in the cases we have to cover.

*/

package cake.warski.bakery

import cake.warski.bakery.ModuleDependencies.bakery
import cake.warski.bakery.models.Models._

class CornMill(cornField: CornField) extends Mill(cornField)
class WheatMill(wheatField: WheatField) extends Mill(wheatField)

trait MillModule {
  def mill: Mill
}

trait CornMillModule extends MillModule {
  lazy val cornField = new CornField()
  lazy val mill = new CornMill(cornField)
}

trait WheatMillModule extends MillModule {
  lazy val wheatField = new WheatField()
  lazy val mill = new WheatMill(wheatField)
}

trait BakeryModule2 extends MillModule with LivestockModule {
  lazy val bakery = new Bakery(mill, diaryFarm)
}

object Config {
  val cornPreferred = true
}

object MultipleModuleImplementations extends App {

  import Config._

  val modules = if (cornPreferred) {
    new BakeryModule2 with CornMillModule
  } else {
    new BakeryModule2 with WheatMillModule
  }

  val me = new Person

  // using the object graph
  val cake = bakery.bakeCake
  me.eat(cake)
}

/*

Can it be any better?

Sure! There’s always something to improve :). One of the problems was already mentioned
– you cannot choose which trait-module to use dynamically (run-time configuration).

Another area that could get improved is the relation between trait-modules and packages.
A good approach is to have a single trait-module per package (or per package tree).
That way you logically group code that implements some functionality in a single package,
and specify how the classes that form the implementations should be used in the trait-module.
But why then do you have to define both the package and trait-module?
Maybe they can be merged together somehow? Increasing the role of packages is also an idea I’ve been exploring in the Veripacks project.

It may also be good to restrict the visibility of some of the defined objects.
Following the “one public class per package” rule, here we might have “one public object per trait-module”.
However, if we are creating bigger trait-modules out of smaller ones, the bigger module has no way to restrict
the visibility of the objects in the module it composes of. In fact, the smaller modules
would have to know the maximum scope of their visibility and use an appropriate private[package name] modifier
(supposing the bigger module is in a parent package).

Summing up

Overall, we found this solution to be a simple, clear way to structure our code and create the object graph.
It uses only native Scala constructs, does not depend on any frameworks or libraries,
and provides compile-time checking that everything is defined properly.

Bon Appetit!


 */