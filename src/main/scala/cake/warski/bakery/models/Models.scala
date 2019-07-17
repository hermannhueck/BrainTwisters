package cake.warski.bakery.models

object Models {

  case class Cake(name: String)

  class Person {
    def eat(cake: Cake): Unit = println(s"Eating $cake")
  }

  abstract class Field
  class CornField extends Field
  class WheatField extends Field
  class Mill(field: Field)
  class CowPasture
  class DiaryFarm(cowPasture: CowPasture)
  class Bakery(mill: Mill, dairyFarm: DiaryFarm) {
    def bakeCake: Cake = {
      val cake = Cake("plum cake")
      println(s"Baking $cake")
      cake
    }
  }
  class EspressoMachine
  class Cafe(bakery: Bakery, espressoMachine: EspressoMachine) {
    def orderCoffeeAndCroissant(): Unit = println("Coffee and Croissant was ordered.")
  }
}
