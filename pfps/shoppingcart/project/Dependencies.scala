import sbt._

object Dependencies {

  object Versions {
    val cats = "2.0.0"
    val catsEffect = "2.0.0"
    val catsRetry = "0.3.1"
    val console4cats = "0.8.0"
    val redis4cats = "0.9.1"
    val fs2 = "2.1.0"
    val log4cats = "1.0.0"
    val newtype = "0.4.3"
    val refined = "0.9.10"

    val betterMonadicFor = "0.3.1"
    val kindProjector = "0.10.3"
    val logback = "1.2.1"
  }

  object Libraries {
    lazy val cats = "org.typelevel" %% "cats-core" % Versions.cats
    lazy val catsEffect = "org.typelevel" %% "cats-effect" % Versions.catsEffect
    lazy val console4cats = "dev.profunktor" %% "console4cats" % Versions.console4cats
    lazy val fs2 = "co.fs2" %% "fs2-core" % Versions.fs2
    lazy val catsRetryCore = "com.github.cb372" %% "cats-retry-core" % Versions.catsRetry
    lazy val catsRetryCatsEffect = "com.github.cb372" %% "cats-retry-cats-effect" % Versions.catsRetry

    lazy val refinedCore = "eu.timepit" %% "refined" % Versions.refined
    lazy val refinedCats = "eu.timepit" %% "refined-cats" % Versions.refined

    lazy val redis4cats = "dev.profunktor" %% "redis4cats-effects" % Versions.redis4cats
    lazy val log4cats = "io.chrisdavenport" %% "log4cats-slf4j" % Versions.log4cats
    lazy val newtype = "io.estatico" %% "newtype" % Versions.newtype

    // Compiler plugins
    lazy val betterMonadicFor = "com.olegpy" %% "better-monadic-for" % Versions.betterMonadicFor
    lazy val kindProjector = "org.typelevel" %% "kind-projector" % Versions.kindProjector

    // Runtime
    lazy val logback = "ch.qos.logback" % "logback-classic" % Versions.logback
  }

}
