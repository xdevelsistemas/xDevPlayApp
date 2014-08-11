// Comment to get more information during initialization
logLevel := Level.Warn

scalaVersion := "2.10.4"

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" )


// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Plugins manager" at "http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"

// Local Ivy repository (see: https://github.com/cheleb/sbt-dart-plugin )
resolvers += Resolver.file("Local Repository", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.3")

//package manager
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "0.7.1")