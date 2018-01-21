// Dependencies
lazy val twitterUtilCore = "com.twitter" %% "util-core"
lazy val twitterUtilCache = "com.twitter" %% "util-cache"
lazy val twitterUtilCacheGuava = "com.twitter" %% "util-cache-guava"
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
libraryDependencies += twitterUtilCacheGuava % twitterUtilVersion

libraryDependencies += scalactic % scalacticVersion % "test"
libraryDependencies += scalatest % scalaTestVersion % "test"

homepage := Some(url("https://github.com/yuk1ty/twitter-cache"))
scmInfo := Some(
  ScmInfo(url("https://github.com/yuk1ty/twitter-cache"),
          "git@github.com:yuk1ty/twitter-cache.git"))
developers := List(
  Developer("yuk1ty",
            "Yuki Toyoda",
            "yuki.multiplus@gmail.com",
            url("https://github.com/yuk1ty")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
