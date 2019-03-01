import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

val http4sVersion = "0.20.0-M6"

lazy val root = (project in file("."))
  .settings(
    name := "poc-listener-scala",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.keycloak" % "keycloak-core" % "4.8.3.Final",
    libraryDependencies += "org.keycloak" % "keycloak-server-spi" % "4.8.3.Final" % "provided",
    libraryDependencies += "org.keycloak" % "keycloak-server-spi-private" % "4.8.3.Final" % "provided",
    libraryDependencies += "org.http4s" %% "http4s-dsl" % http4sVersion,
    libraryDependencies += "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  )

assemblyJarName in assembly := "myListener.jar"
