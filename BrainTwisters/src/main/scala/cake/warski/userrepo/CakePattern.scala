package cake.warski.userrepo

import org.mockito.Mockito

object CakePattern {

  /*

  http://www.cakesolutions.net/teamblogs/2011/12/19/cake-pattern-in-depth

  Dependency Injection in Scala: Extending the Cake Pattern

  By Adam Warski14 December, 201017 Comments

  Continuing the mini-series on Dependency Injection (see my previous blogs: problems with DI, assisted inject for CDI and improving assisted inject),
  I took a look at how DI is handled in Scala.

  There are several approaches, one of the most interesting being the Cake Pattern. It is a DI solution that uses only native language features,
  without any framework support. For a good introduction see either Jonas Boner’s blog (on which this post is largerly based)
  or Martin Odersky’s paper Scalable Component Abstractions.

  I would like to extend the Cake Pattern to allow defining dependencies which need some user-provided data to be constructed
  (like in autofactories/assisted inject).

  THE CAKE PATTERN: INTERFACES

  But let’s start with an example of the base pattern. Let’s say that we have a User class,

  */

  sealed case class User(username: String)

  /*

  and that we want to create a UserRepository service. Using the Cake Pattern, first we create the “interface”:

   */

  trait UserRepositoryComponent { // For expressing dependencies

    def userRepository: UserRepository // Way to obtain the dependency

    trait UserRepository { // Interface exposed to the user
      def find(username: String): User
    }
  }

  /*

  We have three important things here:

  - the UserRepositoryComponent trait will be used to express dependencies. It contains the component definition, consiting of:
  - a way to obtain the dependency: the def userRepository method (could also be a val, but why a def is better I’ll explain later)
  - the interface itself, here a UserRepository trait, which gives the functionality of locating users by username

  THE CAKE PATTERN: IMPLEMENTATIONS

  An implementation of a component looks pretty similar:

   */

  trait UserRepositoryComponentHibernateImpl
    extends UserRepositoryComponent {

    def userRepository = new UserRepositoryImpl

    class UserRepositoryImpl extends UserRepository {
      def find(username: String): User = {
        println("Find with Hibernate: " + username)
        User(username)
      }
    }
  }

  /*

  Nothing special here. The component implementation extends the “interface” component trait.
  This brings into scope the UserRepository trait, which can be implemented.

  USING DEPENDENCIES

  How can one component/service say that it depends on another? Scala’s self-type annotations are of much use here.
  For example, if a UserAuthorization component requires the UserRepository, we can write this as follows:

   */

  // Component definition, as before
  trait UserAuthorizationComponent {

    def userAuthorization: UserAuthorization

    trait UserAuthorization {
      def authorize(user: User)
    }
  }

  // Component implementation
  trait UserAuthorizationComponentImpl
    extends UserAuthorizationComponent {

    // Dependencies
    this: UserRepositoryComponent =>

    def userAuthorization = new UserAuthorizationImpl

    class UserAuthorizationImpl extends UserAuthorization {
      def authorize(user: User) {
        println("Authorizing " + user.username)
        // Obtaining the dependency and calling a method on it
        userRepository.find(user.username)
      }
    }
  }

  /*

  The important part here is
    this: UserRepositoryComponent =>.
  By this code fragment we specify that the UserAuthorizationComponentImpl requires some implementation of the UserRepositoryComponent.
  This also brings the content of the UserRepositoryComponent into scope, so both the method to obtain the user repository
  and the UserRepository trait itself are visible.

  WIRING

  How do we wire different components together? Again quite easily. For example:

   */

  val env = new UserAuthorizationComponentImpl
    with UserRepositoryComponentHibernateImpl

  env.userAuthorization.authorize(User("1"))

  /*

  First we need to construct the environment, by combining all of the components implementations that we want to use into a single object.
  Next, we can call methods on the environment to obtain services.

  What about testing? Also easy:

   */

  val envTesting = new UserAuthorizationComponentImpl
    with UserRepositoryComponent {
    def userRepository = Mockito.mock(classOf[UserRepository])
  }

  envTesting.userAuthorization.authorize(User("3"))

  /*

  Here we have mocked the user repository, so we can test the UserAuthorizationComponentImpl in isolation.

  DEFS OVER VALS

  Why are defs in the component definition better as the way to obtain the dependency?
  Because if you use a val, all implementations are locked and have to provide a single dependency instance (a constant).
  With a method, you can return different values on each invocation. For example, in a web environment, this is a great way to implement scoping!
  The method can read from the request or session state. Of course, it is still possible to provide a singleton.
  Or a new instance of the dependency on each invocation.

  DEPENDENCIES THAT NEED USER DATA

  Finally, we arrive to the main point. What if our dependencies need some data at runtime?
  For example, if we wanted to create a UserInformation service, which wraps a User instance?

  Well, who said the the methods by which we obtain the dependencies need to be parameterless?

   */

  /*
    // Interface
    trait UserInformationComponent {
      // What is needed to create the component
      def userInformation(user: User)

      trait UserInformation {
        def userCountry: Country
      }
    }

    // Implementation
    trait UserInformationComponentImpl
      extends UserInformationComponent {
      // Dependencies
      this: CountryRepositoryComponent =>

      def userInformation(user: User) = new UserInformationImpl(user)

      class UserInformationImpl(val user: User) extends UserInformation {
        def userCountry: Country {
          // Using the dependency
          countryRepository.findByEmail(user.email)
        }
      }
    }

    // Usage
    val env = new UserInformationComponentImpl
      with CountryRepositoryComponentImpl
    env.userInformation(User("someuser@domain.pl")).userCountry
  */

  /*

  Isn’t this better than passing the User instance as a method parameter?

  Using the Cake Pattern, creating stateful dependencies, which can be created at run-time with user-provided data,
  and still depend on other components is a breeze. This is similar to a factory method, however with much less noise.

  THE GOOD AND THE BAD

  The good:

  - no framework required, using only language features
  - type safe – a missing dependency is found at compile-time
  - powerful – “assisted inject”, scoping possible by implementing the dependency-providing method appropriately

  The bad:

  - quite a lot of boilerplate code: each component has a component interface, implementation, service interface and service implementation

  However, I don’t think defining all four parts is always necessary. If there’s only one implementation of a component,
  you can combine the component interface and implementation into one, and if there’s a need, refactor later.

  Adam

   */
}
