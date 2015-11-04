name := "shapelessdemo"
organization := "com.ntoggle"
scalaVersion := "2.11.7"

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Scalaz Bintray" at "http://dl.bintray.com/scalaz/releases",
  "Twitter" at "http://maven.twttr.com")

libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-json" % "2.4.2",
    "com.chuusai" %% "shapeless" % "2.2.5",
    "org.scalaz" %% "scalaz-core" % "7.1.4")

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-deprecation",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-encoding", "UTF-8",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard"
)

initialCommands in console := "import scalaz._, Scalaz._"
