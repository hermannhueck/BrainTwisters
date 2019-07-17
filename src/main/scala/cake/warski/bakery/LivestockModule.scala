package cake.warski.bakery

import cake.warski.bakery.models.Models.{CowPasture, DiaryFarm}

trait LivestockModule {
  lazy val cowPasture = new CowPasture()
  lazy val diaryFarm = new DiaryFarm(cowPasture)
}
