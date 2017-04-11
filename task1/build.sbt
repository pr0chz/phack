name := "task1"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.5.14"
libraryDependencies += "io.circe" % "circe-core_2.11" % "0.7.1"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3"

val http4sVersion = "0.17.0-M1"

// Only necessary for SNAPSHOT releases
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
)

mainClass in Compile := Some("APIService")