name := """BrainTwisters"""

version := "1.0"

scalaVersion := "2.12.3"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard"
)

libraryDependencies ++= Seq(
  // "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "org.scalacheck" %% s"scalacheck" % "1.13.4" % "test",
  "org.scalatest" %% s"scalatest" % "3.2.0-SNAP3" % "test",
  "org.mockito" % "mockito-core" % "2.7.22",
  "com.google.inject" % "guice" % "4.1.0",
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "io.vavr" % "vavr" % "0.9.0",
  "org.jetbrains.kotlin" % "kotlin-stdlib" % "1.1.4",
  "org.jetbrains.kotlin" % "kotlin-stdlib-jre8" % "1.1.4"
)
