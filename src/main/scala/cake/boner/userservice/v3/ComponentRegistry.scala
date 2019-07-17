package cake.boner.userservice.v3

object ComponentRegistry extends
  UserServiceComponent with
  UserRepositoryComponent {

  override val userService: ComponentRegistry.UserService = new UserService
  override val userRepository: ComponentRegistry.UserRepository = new UserRepository
}