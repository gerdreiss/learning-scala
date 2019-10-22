import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github"
ThisBuild / organizationName := "none"

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
      Libraries.refinedCore
    )
  )
