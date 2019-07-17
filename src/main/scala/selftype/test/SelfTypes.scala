package selftype.test

object SelfTypes {

  trait RequiredForAllAs

  trait A {
    self: RequiredForAllAs =>
  }

  trait B extends A with RequiredForAllAs
}
