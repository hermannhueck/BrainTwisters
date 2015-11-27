name := """BrainTwisters"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  // "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.scalacheck" %% "scalacheck" % "1.12.0" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)
