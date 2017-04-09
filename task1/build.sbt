name := "task1"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.5.14"

mainClass in Compile := Some("HubStar")