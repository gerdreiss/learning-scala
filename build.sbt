name := "learning-scala"

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "org.scala-lang" % "scala-compiler" % scalaVersion.value % "scala-tool",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scalactic" %% "scalactic" % "3.0.0",
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

//libraryDependencies ++= Seq(
//  "org.specs2" %% "specs2-core" % "3.8.5" % "test"
//)

scalacOptions in Test ++= Seq("-Yrangepos")