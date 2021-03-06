import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github"
ThisBuild / organizationName := "github"

lazy val root = (project in file("."))
  .settings(
    name := "ShoppingCart",
    scalacOptions += "-Ymacro-annotations",
    libraryDependencies ++= Seq(
      compilerPlugin(Libraries.kindProjector),
      compilerPlugin(Libraries.betterMonadicFor),
      Libraries.cats,
      Libraries.catsEffect,
      Libraries.console4cats,
      Libraries.fs2,
      Libraries.newtype,
      Libraries.refinedCore,
      Libraries.meowMtl,
      Libraries.http4sCore,
      Libraries.http4sDsl
    )
  )
