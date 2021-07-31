val scala3Version = "3.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-sandbox",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies += "org.scalameta" %% "munit-scalacheck" % "0.7.27" % "test",
    testFrameworks += new TestFramework("munit.Framework")
  )
