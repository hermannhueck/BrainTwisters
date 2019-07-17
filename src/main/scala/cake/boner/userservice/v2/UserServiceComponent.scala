package cake.boner.userservice.v2

// using self-type annotation declaring the dependencies this
// component requires, in our case the UserRepositoryComponent
trait UserServiceComponent {

  self: UserRepositoryComponent =>

  val userService: UserService = new UserService

  class UserService {
    def authenticate(username: String, password: String): User =
      userRepository.authenticate(User(username, password))
    def create(username: String, password: String) =
      userRepository.create(User(username, password))
    def delete(user: User) = userRepository.delete(user)
  }
}
