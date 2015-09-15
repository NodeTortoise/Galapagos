import com.typesafe.sbt.web.Import.WebKeys.webJarsDirectory

import org.nlogo.PlayScrapePlugin.credentials.{ fromCredentialsProfile, fromEnvironmentVariables }

name := "Galapagos"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:_",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala, org.nlogo.PlayScrapePlugin)

val tortoiseVersion = "0.1-0aff5c0"

libraryDependencies ++= Seq(
  "org.nlogo" % "tortoise" % tortoiseVersion,
  "org.nlogo" % "netlogowebjs" % tortoiseVersion,
  cache,
  "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
  "org.scalatestplus" %% "play" % "1.4.0-M3" % "test"
)

libraryDependencies ++= Seq(
  "org.webjars" % "chosen" % "1.3.0",
  "org.webjars.npm" % "filesaver.js" % "0.1.1",
  "org.webjars" % "highcharts" % "4.1.6",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars" % "mousetrap" % "1.4.6",
  "org.webjars" % "markdown-js" % "0.5.0-1",
  "org.webjars" % "ractive" % "0.7.1",
  "org.webjars" % "codemirror" % "5.4"
)

resolvers += bintray.Opts.resolver.repo("netlogo", "TortoiseAux")

resolvers += bintray.Opts.resolver.repo("netlogo", "NetLogoHeadless")

resolvers += Resolver.file("Local repo", file(System.getProperty("user.home") + "/.ivy2/local"))(Resolver.ivyStylePatterns)

pipelineStages in Assets += autoprefixer

fork in Test := false

includeFilter in autoprefixer := Def.setting {
  val webJarDir     = (webJarsDirectory in Assets).value.getPath
  val testWebJarDir = (webJarsDirectory in TestAssets).value.getPath
  new FileFilter {
    override def accept(file: java.io.File) = {
      file.getName.endsWith(".css") && ! (file.getPath.contains(webJarDir) || file.getPath.contains(testWebJarDir))
    }
  }
}.value

routesGenerator := InjectedRoutesGenerator

scrapeRoutes ++= Seq("/create-standalone", "/tortoise", "/model/list.json", "/model/statuses.json", "/netlogo-engine.js", "/netlogo-agentmodel.js", "/netlogoweb.js")

scrapeDelay := 20

scrapePublishCredential <<= Def.settingDyn {
  if (System.getenv("TRAVIS") == "true")
    Def.setting { fromEnvironmentVariables }
  else
    // Requires setting up a credentials profile, ask Robert for more details
    Def.setting { fromCredentialsProfile("NetLogoWebAdmin") }
}


scrapePublishBucketID <<= Def.settingDyn {
  val branchDeploy = Map("wip-static-site" -> "sample-cdn")

  if (System.getenv("TRAVIS") == "true")
    Def.setting { branchDeploy.get(System.getenv("TRAVIS_BRANCH")) }
  else
    Def.setting { branchDeploy.get("wip-static-site") }
}

scrapePublishDistributionID <<= Def.settingDyn {
  val branchPublish = Map("wip-static-site" -> "E3864GFW54OVD0")

  if (System.getenv("TRAVIS") == "true")
    Def.setting { branchPublish.get(System.getenv("TRAVIS_BRANCH")) }
  else
    Def.setting { branchPublish.get("wip-static-site") }
}
