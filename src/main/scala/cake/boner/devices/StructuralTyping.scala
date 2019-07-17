package cake.boner.devices

import scala.language.reflectiveCalls

object StructuralTyping {

  // =======================
  // service interfaces
  trait OnOffDevice {
    def on(): Unit
    def off(): Unit
  }
  trait SensorDevice {
    def isCoffeePresent: Boolean
  }

  // =======================
  // service implementations
  class Heater extends OnOffDevice {
    def on(): Unit = println("heater.on")
    def off(): Unit = println("heater.off")
  }
  class PotSensor extends SensorDevice {
    def isCoffeePresent = true
  }

  // =======================
  // service declaring two dependencies that it wants injected,
  // is using structural typing to declare its dependencies
  class Warmer(env: {
    val potSensor: SensorDevice
    val heater: OnOffDevice
  }) {
    def trigger(): Unit = {
      if (env.potSensor.isCoffeePresent) env.heater.on()
      else env.heater.off()
    }
  }

  class Client(env : { val warmer: Warmer }) {
    env.warmer.trigger()
  }

  // =======================
  // instantiate the services in a configuration module
  object Config {
    lazy val potSensor = new PotSensor
    lazy val heater = new Heater
    lazy val warmer = new Warmer(this) // this is where injection happens
  }

  new Client(Config)
}
