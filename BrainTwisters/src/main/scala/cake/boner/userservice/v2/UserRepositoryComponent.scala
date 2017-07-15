package cake.boner.userservice.v2

trait UserRepositoryComponent {

  val userRepository = new UserRepository

  class UserRepository {

    def authenticate(user: User): User = {
      println("authenticating user: " + user)
      user
    }

    def create(user: User) = println("creating user: " + user)

    def delete(user: User) = println("deleting user: " + user)
  }

}
