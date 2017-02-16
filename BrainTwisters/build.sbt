name := """BrainTwisters"""

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  // "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.scalacheck" %% s"scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% s"scalatest" % "3.2.0-SNAP3" % "test",
  "io.javaslang" % "javaslang" % "2.0.5"
)
