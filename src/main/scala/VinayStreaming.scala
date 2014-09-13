import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.storage.StorageLevel
import org.apache.spark.examples.streaming._

object VinayStreaming {

  def main (args: Array[String]) {
    if (args.length < 2) {
      System.err.print("Need to specify 2 parameters!!")
      System.exit(1)
    }

    StreamingExamples.setStreamingLogLevels()

    val sparkConf = new SparkConf().setAppName("VinayStreaming")
    val sparkStreamingContext = new StreamingContext(sparkConf, Seconds(2))

    val lines = sparkStreamingContext.socketTextStream(args(0), args(1).toInt, StorageLevel.MEMORY_AND_DISK_SER )
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.print()
    sparkStreamingContext.start()
    sparkStreamingContext.awaitTermination()
  }

}
