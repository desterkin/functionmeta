val scalaVersions = Seq("2.12.8", "2.13.0")

ThisBuild / version := "0.2.3"
ThisBuild / organization := "com.github.katlasik"
ThisBuild / name := "functionmeta"
ThisBuild / crossScalaVersions := scalaVersions
ThisBuild / scalaVersion := "2.13.0"

lazy val root = (project in file("."))
  .settings(
    name := "functionmeta",
    homepage := Some(url("https://github.com/katlasik/functionmeta")),
    scalaVersion := "2.13.0",
    crossScalaVersions := scalaVersions,
    sonatypeProfileName := "com.github.katlasik",
    publishMavenStyle := true,
    licenses := Seq("ISC" -> url("https://opensource.org/licenses/ISC")),
    scmInfo := Some(
      ScmInfo(url("https://github.com/katlasik/functionmeta"), "git@github.com:katlasik/functionmeta.git")
    ),
    libraryDependencies ++= Seq(
      "org.scalameta" %% "scalameta" % "4.2.0",
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "com.chuusai" %% "shapeless" % "2.3.3" % "test",
      "org.scalatest" %% "scalatest" % "3.0.8" % "test"
    ),
    developers := List(
      Developer(
        id="katlasik",
        name="Krzysztof Atlasik",
        email="krzysztof.atlasik@pm.me",
        url("https://github.com/katlasik/functionmeta")
      )
    )
  )

Compile / scalacOptions ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => "-Ymacro-annotations" :: Nil
    case _ => Nil
  }
}

libraryDependencies ++= {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, n)) if n >= 13 => Nil
    case _ =>
      compilerPlugin(
        "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
      ) :: Nil
  }
}

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding",
  "utf-8",
  "-explaintypes",
  "-feature",
  "-language:existentials",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xcheckinit",
  "-Xlint:adapted-args",
  "-Xlint:constant",
  "-Xlint:delayedinit-select",
  "-Xlint:doc-detached",
  "-Xlint:inaccessible",
  "-Xlint:infer-any",
  "-Xlint:missing-interpolator",
  "-Xlint:nullary-override",
  "-Xlint:nullary-unit",
  "-Xlint:option-implicit",
  "-Xlint:package-object-classes",
  "-Xlint:poly-implicit-overload",
  "-Xlint:private-shadow",
  "-Xlint:stars-align",
  "-Xlint:type-parameter-shadow",
  "-Ywarn-dead-code",
  "-Ywarn-extra-implicit",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ywarn-value-discard"
)
