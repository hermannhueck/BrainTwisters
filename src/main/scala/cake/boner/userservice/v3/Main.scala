package cake.boner.userservice.v3

object Main extends App {

  val userService = ComponentRegistry.userService
  //...
  val user = userService.authenticate("Hugo", "Nasenbaer")

  println("<-- Done")
}
