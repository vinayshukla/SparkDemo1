package com.whiteware.sparkdemo;
import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.Function;

public class SimpleApp {

    public static void main(String[] args) {
        String logFile = "hdfs://sandbox.hortonworks.com:8020/tmp/data";
        SparkConf conf = new SparkConf().setAppName("Simple Application");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAdds = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("add");
            }
        }).count();

        long numSecuritys = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) {
                return s.contains("security");
            }
        }).count();

        System.out.println("Lines with add: " + numAdds + ", lines with security: " + numSecuritys);
    }

}
