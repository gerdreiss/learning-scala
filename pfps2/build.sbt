ThisBuild / scalaVersion := "2.13.7"

lazy val root = (project in file("."))
  .settings(
    name := "minimal",
    libraryDependencies ++= Seq(
      compilerPlugin(
        "org.typelevel" %% "kind-projector" % "0.13.2"
          cross CrossVersion.full
      ),
      "org.typelevel" %% "cats-core"             % "2.6.1",
      "org.typelevel" %% "cats-effect"           % "3.2.9",
      "org.typelevel" %% "cats-mtl"              % "1.2.1",
      "co.fs2"        %% "fs2-core"              % "3.2.2",
      "dev.optics"    %% "monocle-core"          % "3.1.0",
      "dev.optics"    %% "monocle-macro"         % "3.1.0",
      "io.estatico"   %% "newtype"               % "0.4.4",
      "eu.timepit"    %% "refined"               % "0.9.27",
      "eu.timepit"    %% "refined-cats"          % "0.9.27",
      "tf.tofu"       %% "derevo-cats"           % "0.12.7",
      "tf.tofu"       %% "derevo-cats-tagless"   % "0.12.7",
      "tf.tofu"       %% "derevo-circe-magnolia" % "0.12.7",
      "tf.tofu"       %% "tofu-core-higher-kind" % "0.10.6"
    ),
    scalacOptions ++= Seq(
      "-Ymacro-annotations",
      "-Wconf:cat=unused:info"
    )
  )
addCommandAlias("runLinter", ";scalafixAll --rules OrganizeImports")
