package cake.boner.userservice.v2

object Main extends App {

  val userService = ComponentRegistry.userService
  //...
  val user = userService.authenticate("Hugo", "Nasenbaer")

  println("<-- Done")
}
