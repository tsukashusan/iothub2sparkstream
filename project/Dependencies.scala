import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4"
  lazy val spakCore = "org.apache.spark" %% "spark-core" % "2.1.1.2.6.2.35-1" % "provided" exclude("org.apache.zookeeper", "zookeeper")
  lazy val spakSQL = "org.apache.spark" %% "spark-sql" % "2.1.1.2.6.2.35-1" % "provided" exclude("org.apache.zookeeper", "zookeeper")
  lazy val spakStreaming = "org.apache.spark" %% "spark-streaming" % "2.1.1.2.6.2.35-1" % "provided" exclude("org.apache.zookeeper", "zookeeper")
  lazy val spakMlib = "org.apache.spark" %%  "spark-mllib" % "2.1.1.2.6.2.35-1" % "provided" exclude("org.apache.zookeeper", "zookeeper")
  lazy val eventHubSparkStream = "com.microsoft.azure" %% "azure-eventhubs-spark" % "2.1.6"
  //lazy val eventHubs = "com.microsoft.azure" % "azure-eventhubs" % "1.0.0"
  //azy val servicebus = "com.microsoft.azure" % "azure-servicebus" % "1.2.4-PREVIEW"
}
