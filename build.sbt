name := "learning-scala"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "scala-tool",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",
  "org.scala-lang.modules" %% "scala-swing" % "2.0.0",
  "com.github.mpilquist" %% "simulacrum" % "0.10.0",
  "org.scalaz" %% "scalaz-core" % "7.2.12",
  "org.scalaz" %% "scalaz-concurrent" % "7.2.12",
  "com.typesafe.akka" %% "akka-actor" % "2.5.1",
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0"
  cross CrossVersion.full)

//libraryDependencies ++= Seq(
//  "org.specs2" %% "specs2-core" % "3.8.5" % "test"
//)

scalacOptions in Test ++= Seq("-Yrangepos")