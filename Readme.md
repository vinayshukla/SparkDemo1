SparkDemo
=========

This maven project creates a Simple Application using Apache Spark that can be run against a Hadoop Cluster.
The project uses Hortonwork's maven repository to resolve Spark depedencies.

To package the application

>mvn clean package

One of the artifact this will produce is ../target/SparkDemo-1.1.0.jar

Copy the jar to your Hadoop Cluster. In this example I am pushing the 
produced SparkDemo jar to an HDP 2.1 Sandbox
> scp -P 2222 target/SparkDemo-1.1.0.jar root@127.0.0.1:/root

Go to your Hadoop cluster & ensure you have set the YARN_CONF_DIR
cd to your Spark home dir and run the following, ensure the path to SparkDemo-1.1.0.jar is where you copied the SparkDemo on your Hadoop cluster

./bin/spark-submit --class com.whiteware.sparkdemo.SimpleApp --master yarn-cluster --num-executors 3 --driver-memory 512m --executor-memory 512m --executor-cores 1 ../SparkDemo-1.1.0.jar

Running the spark-submit will produce an output similar to 

14/09/12 14:47:39 INFO yarn.Client: Application report from ResourceManager: 
	 application identifier: application_1410558108229_0001
	 appId: 1
	 clientToAMToken: null
	 appDiagnostics: 
	 appMasterHost: sandbox.hortonworks.com
	 appQueue: default
	 appMasterRpcPort: 0
	 appStartTime: 1410558409206
	 yarnAppState: FINISHED
	 distributedFinalState: SUCCEEDED
	 appTrackingUrl: http://sandbox.hortonworks.com:8088/proxy/application_1410558108229_0001/A
	 appUser: root

Go to http://sandbox.hortonworks.com:8088/proxy/application_1410558108229_0001/A

& click on the Logs dir link

The logs will show something like 
.....

14/09/12 14:47:40 INFO storage.BlockManagerMaster: BlockManagerMaster stopped
14/09/12 14:47:40 INFO spark.SparkContext: Successfully stopped SparkContext

Log Type: stdout
Log Length: 43
Lines with add: 4, lines with security: 10


