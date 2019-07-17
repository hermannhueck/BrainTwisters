package cake.boner.devices

import javax.inject.Inject

import com.google.inject.{Binder, Guice, Module}

object GoogelGuice {

  // =======================
  // service interfaces
  trait OnOffDevice {
    def on(): Unit
    def off(): Unit
  }
  trait SensorDevice {
    def isCoffeePresent: Boolean
  }
  trait IWarmer {
    def trigger()
  }
  trait Client

  // =======================
  // service implementations
  class Heater extends OnOffDevice {
    def on(): Unit = println("heater.on")
    def off(): Unit = println("heater.off")
  }
  class PotSensor extends SensorDevice {
    def isCoffeePresent = true
  }
  @Inject class Warmer (
  val potSensor: SensorDevice,
  val heater: OnOffDevice)
  extends IWarmer {

    def trigger(): Unit = {
      if (potSensor.isCoffeePresent) heater.on()
      else heater.off()
    }
  }

  // =======================
  // client
  @Inject class MyClient (val warmer: Warmer) extends Client {
    warmer.trigger()
  }

  // =======================
  // Guice's configuration class that is defining the
  // interface-implementation bindings
  class DependencyModule extends Module {
    def configure(binder: Binder): Unit = {
      binder.bind(classOf[OnOffDevice]).to(classOf[Heater])
      binder.bind(classOf[SensorDevice]).to(classOf[PotSensor])
      binder.bind(classOf[IWarmer]).to(classOf[Warmer])
      binder.bind(classOf[Client]).to(classOf[MyClient])
    }
  }

  // =======================
  // Usage: val bean = new Bean with ServiceInjector
  trait ServiceInjector {
    ServiceInjector.inject(this)
  }

  // helper companion object
  object ServiceInjector {
    import scala.collection.JavaConverters._
    private val modules = Array[Module](new DependencyModule)
    private val jModules = asJavaIterable(modules)
    private val injector = Guice.createInjector(jModules)
    private def inject(obj: AnyRef) = injector.injectMembers(obj)
  }

  // =======================
  // mix-in the ServiceInjector trait to perform injection
  // upon instantiation

  //val client = new MyClient with ServiceInjector  // ??? doesn't compile. Why?

  //println(client)
}
