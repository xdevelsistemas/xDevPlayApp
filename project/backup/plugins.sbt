// Comment to get more information during initialization
logLevel := Level.Warn

scalaVersion := "2.10.3"

scalacOptions ++= Seq( "-deprecation", "-unchecked", "-feature" )


// The Typesafe repository
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

// Local Ivy repository (see: https://github.com/cheleb/sbt-dart-plugin )
resolvers += Resolver.file("Local Repository", file(Path.userHome.absolutePath + "/.ivy2/local"))(Resolver.ivyStylePatterns)


// Netbeans SBT plugin
addSbtPlugin("org.netbeans.nbsbt" % "nbsbt-plugin" % "1.1.2")

// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.2.2") // System.getProperty("play.version"))
