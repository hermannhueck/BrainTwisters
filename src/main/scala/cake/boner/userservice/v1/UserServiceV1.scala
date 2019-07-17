package cake.boner.userservice.v1

object UserServiceV1 {

  case class User(username: String, password: String)

  // a dummy service that is not persisting anything
  // solely prints out info
  class UserRepository {
    def authenticate(user: User): User = {
      println("authenticating user: " + user)
      user
    }

    def create(user: User) = println("creating user: " + user)

    def delete(user: User) = println("deleting user: " + user)
  }

  class UserService(val userRepository: UserRepository) {

    def authenticate(username: String, password: String): User =
      userRepository.authenticate(User(username, password))

    def create(username: String, password: String) =
      userRepository.create(new User(username, password))

    def delete(user: User) = // All is statically typed.
      userRepository.delete(user)
  }
}
