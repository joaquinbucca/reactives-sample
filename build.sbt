name := "boca-twitter-stream"

version := "1.0"

scalaVersion := "2.11.8"

// https://mvnrepository.com/artifact/com.twitter/hbc-core

libraryDependencies ++= {
  val akkaV       = "2.4.11"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" % "akka-http-testkit_2.11" % akkaV,


    "com.twitter" % "hbc-core" % "2.2.0",
    "io.reactivex" %% "rxscala" % "0.26.5"

  )
}
