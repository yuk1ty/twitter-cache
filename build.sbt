// Dependencies
lazy val twitterUtilCore = "com.twitter" %% "util-core"
lazy val twitterUtilCache = "com.twitter" %% "util-cache"
lazy val scalactic = "org.scalactic" %% "scalactic"
lazy val scalatest = "org.scalatest" %% "scalatest"

// Version
lazy val twitterUtilVersion = "18.1.0"
lazy val scalaTestVersion = "3.0.4"
lazy val scalacticVersion = "3.0.4"

name := "twitter-cache"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += twitterUtilCore % twitterUtilVersion
libraryDependencies += twitterUtilCache % twitterUtilVersion

libraryDependencies += scalactic % scalacticVersion % "test"
libraryDependencies += scalatest % scalaTestVersion % "test"