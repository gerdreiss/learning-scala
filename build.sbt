name := "learning-scala"

version := "1.0"

scalaVersion := "2.12.3"

val scalazVersion = "7.2.15"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "scala-tool",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,
  "org.scalaz" %% "scalaz-concurrent" % scalazVersion,
  "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test",
  "com.typesafe.akka" %% "akka-actor" % "2.5.1",
  "org.typelevel" %% "cats" % "0.9.0",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

scalacOptions += "-feature"

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0"
  cross CrossVersion.full)

//libraryDependencies ++= Seq(
//  "org.specs2" %% "specs2-core" % "3.8.5" % "test"
//)

scalacOptions in Test ++= Seq("-Yrangepos")

initialCommands in console := "import scalaz._, Scalaz._\n"
