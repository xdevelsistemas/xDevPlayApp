// Comment to get more information during initialization
logLevel := Level.Warn

scalaVersion := "2.10.4"

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" )


// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Local Ivy repository (see: https://github.com/cheleb/sbt-dart-plugin )
resolvers += Resolver.file("Local Repository", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.3")
//addSbtPlugin("play" % "sbt-plugin" % "2.2.1")