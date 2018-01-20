// Dependencies
lazy val twitterUtilCore = "com.twitter" %% "util-core"
lazy val twitterUtilCache = "com.twitter" %% "util-cache"

// Version
lazy val twitterUtilVersion = "18.1.0"

name := "twitter-cache"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += twitterUtilCore % twitterUtilVersion
libraryDependencies += twitterUtilCache % twitterUtilVersion