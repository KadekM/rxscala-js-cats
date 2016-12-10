val scalaV = "2.11.8"
val crossScalaV = Seq("2.11.8", "2.12.1")

// ---- formatting ----
scalaVersion in ThisBuild := scalaV
scalafmtConfig in ThisBuild := Some(file(".scalafmt.conf"))

// ---- common settings ----

val commonSettings = Seq(
  organization := "com.marekkadek",
  scalaVersion := scalaV,
  crossScalaVersions := crossScalaV,
  releaseCrossBuild := true,
  scalacOptions := Seq(
    // following two lines must be "together"
    "-encoding",
    "UTF-8",
    "-Xlint",
    "-Xlint:missing-interpolator",
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Ywarn-dead-code",
    "-Yno-adapted-args",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-Ywarn-value-discard",
    "-Ywarn-unused-import",
    "-Ywarn-unused",
    "-Ywarn-numeric-widen"
  )
)

// ---- publising ----

val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)

val publishSettings = Seq(
  homepage := Some(url("https://github.com/KadekM/rxscala-js-cats")),
  organizationHomepage := Some(
    url("https://github.com/KadekM/rxscala-js-cats")),
  licenses += ("MIT license", url(
    "http://www.opensource.org/licenses/mit-license.php")),
  publishMavenStyle := true,
  publishArtifact in Test := false,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  pomIncludeRepository := { _ =>
    false
  },
  pomExtra :=
    <scm>
      <url>git@github.com:kadekm/rxscala-js-cats.git</url>
      <connection>scm:git:git@github.com:kadekm/rxscala-js-cats.git</connection>
    </scm>
      <developers>
        <developer>
          <id>kadekm</id>
          <name>Marek Kadek</name>
          <url>https://github.com/KadekM</url>
        </developer>
      </developers>
)

// ---- modules ----

lazy val cats = Project(id = "rxscala-js-cats", base = file("modules/cats"))
  .settings(
    commonSettings,
    publishSettings,
    libraryDependencies ++= Seq(
      "com.github.lukajcb" %%% "rxscala-js" % "0.12.0",
      "org.typelevel" %%% "cats" % "0.7.2", // todo upgrade once we can, and crosspublish...
      "org.scalatest" %%% "scalatest" % "3.0.1" % Test,
      "org.scalacheck" %%% "scalacheck" % "1.13.4" % Test
    ),
    jsDependencies += "org.webjars.npm" % "rxjs" % "5.0.0-rc.4" / "bundles/Rx.min.js" commonJSName "Rx"
  )
  .enablePlugins(ScalaJSPlugin)

lazy val root = Project(id = "root", base = file("."))
  .settings(
    commonSettings,
    noPublishSettings
  )
  .dependsOn(cats)
  .aggregate(cats)
