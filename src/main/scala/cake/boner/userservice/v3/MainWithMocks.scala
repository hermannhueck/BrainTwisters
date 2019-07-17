package cake.boner.userservice.v3

object MainWithMocks extends App {

  val userService = TestEnvironment
    .userService
  //...
  val user = userService.authenticate("Hugo", "Nasenbaer")

  println("<-- Done")
}
