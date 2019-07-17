package cake.boner.userservice.v3

import org.mockito.Mockito

trait MockTrait {

  def mock[T](cl: Class[T]): T = Mockito.mock(cl)
}
