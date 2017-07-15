package cake.warski.bakery

import cake.warski.bakery.models.Models.{Mill, WheatField}

trait CropModule {
  lazy val wheatField = new WheatField()
  lazy val mill = new Mill(wheatField)
}
