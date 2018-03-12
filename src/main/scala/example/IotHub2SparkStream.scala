package example

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.ProcessingTime

object IotHub2SparkStream extends Greeting with App {
  val rootLogger = Logger.getRootLogger().setLevel(Level.ERROR)
  println(greeting)
  
  val SPARK_SESSION = SparkSession.builder().appName("IotHub2SparkStream")
                                              .getOrCreate()
  SPARK_SESSION.sparkContext.setLogLevel("ERROR")
  loadfromPartition(SPARK_SESSION)                     
 
  def loadfromPartition(spark: SparkSession): Unit = {
    val eventhubParameters = Map[String, String] (
               "eventhubs.policyname" -> "<policyname>",
               "eventhubs.policykey" -> "<policykey>",
               "eventhubs.namespace" -> "<namespace>",
               "eventhubs.name" -> "<name>",
               "eventhubs.partition.count" -> "4",
               "eventhubs.consumergroup" -> "$Default",
               "eventhubs.progressTrackingDir" -> "/eventhubs/progress",
               "eventhubs.maxRate" -> "100",
               "eventhubs.sql.containsProperties" -> "true",
               "eventhubs.sql.userDefinedKeys" -> "creationTime,randomUserProperty"
               )
    val inputStream = spark.readStream
                           .format("eventhubs")
                           .options(eventhubParameters)
                           .load()
    //val streamingQuery1 = inputStream.writeStream.
    //outputMode("append").
    //format("console").start().awaitTermination()
    val streamingQuery1 = inputStream.writeStream.
      outputMode("append").
      trigger(ProcessingTime("10 seconds")).
      option("compress", "zlib").
      option("checkpointLocation", "/tmp/checkpoit/to/checkpoint/dir").
      format("orc").option("path", "/tmp/checkpoint_path" + "/ETL").partitionBy("creationTime").start()
    streamingQuery1.awaitTermination()
  }
}

trait Greeting {
  lazy val greeting: String = "hello"
}
