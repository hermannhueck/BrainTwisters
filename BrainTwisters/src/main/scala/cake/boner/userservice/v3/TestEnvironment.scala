package cake.boner.userservice.v3

object TestEnvironment extends
  UserServiceComponent with
  UserRepositoryComponent with
  MockTrait {

  override val userService: TestEnvironment.UserService = mock(classOf[UserService])
  override val userRepository: TestEnvironment.UserRepository = mock(classOf[UserRepository])
}