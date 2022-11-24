//Practice 2

////////////////////////
/// Tome los datos //////
//////////////////////

//We import the library needed to use Logistic Regression in Spark

import org.apache.spark.ml.classification.LogisticRegression

//We import the library needed to start a session in Spark

import org.apache.spark.sql.SparkSession 

//Optional to avoid warnings

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR) 

//We start the session in spark

val spark = SparkSession.builder().getOrCreate() 

//We read with Spark the data of "Advertising.csv" in CSV format, which we store in Val "data"

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("advertising.csv") 

//We print the schema loaded in "data"

data.printSchema() 
/*root
 |-- Daily Time Spent on Site: double (nullable = true)
 |-- Age: integer (nullable = true)
 |-- Area Income: double (nullable = true)
 |-- Daily Internet Usage: double (nullable = true)
 |-- Ad Topic Line: string (nullable = true)
 |-- City: string (nullable = true)
 |-- Male: integer (nullable = true)
 |-- Country: string (nullable = true)
 |-- Timestamp: timestamp (nullable = true)
 |-- Clicked on Ad: integer (nullable = true)*/

///////////////////////////////
/// Despliegue los datos ////
/////////////////////////////

//We print the first line of the table

data.head(1) 
//res1: Array[org.apache.spark.sql.Row] = Array([68.95,35,61833.9,256.09,Cloned 5thgeneration orchestration,Wrightburgh,0,Tunisia,2016-03-27 00:53:11.0,0])

//With the arrangement we print the header of each column with the first registered value

val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}


/////////////////////////////////////////////////////
//// Preparar el DataFrame para Machine Learning ////
////////////////////////////////////////////////////

//Create a new column called "Hour" that will contain the "Hour of the click", store the data frame in val "timedata"

val timedata = data.withColumn("Hour",hour(data("Timestamp"))) 

//Rename the column "Clicked on Ad" to "label"

val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male") 

//We import the "VectorAssembler" library to be able to use its functions

import org.apache.spark.ml.feature.VectorAssembler 

//We import the "Vectors" library to be able to use its functions

import org.apache.spark.ml.linalg.Vectors 

//We create a new "VectorAssembler" object for the "features", store the data frame in val "assembler"

val assembler = (new VectorAssembler()
                  .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male"))
                  .setOutputCol("features")) 


//We use randomSplit to divide the training into 70 and the test into 30

val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)


///////////////////////////////
// Configure un Pipeline ///////
/////////////////////////////

//We import the "Pipeline" library to be able to use its functions

import org.apache.spark.ml.Pipeline 

//We create a "LogisticRegression" object named "Ir"

val lr = new LogisticRegression() 

//We create a new "Pipeline" object with the elements "assembler, Go", store the data frame in "pipeline" val

val pipeline = new Pipeline().setStages(Array(assembler, lr))

//We adjust "fit" of the pipeline for "training", we store the data frame in val "model"

val model = pipeline.fit(training) 

//We store in "results" the test set using transform

val results = model.transform(test) 


////////////////////////////////////
//// Evaluacion del modelo ////////
//////////////////////////////////

//We import the "MulticlassMetrics" library to use its evaluation functions

import org.apache.spark.mllib.evaluation.MulticlassMetrics 

//We convert the test results into RDD, store the data frame in val "predictionAndLabels"

val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd 

//Start the MulticlassMetrics object called "predictionAndLabels", store in the new data frame "metrics"

val metrics = new MulticlassMetrics(predictionAndLabels) 

//We print "Confusion Matrix" giving us the result a TP = 136, FP = 1, FN = 4, TN = 146, which indicates that it is a good model since there are high rates of PT and TN and low rates of FP and FN

println("Confusion matrix:")
println(metrics.confusionMatrix) 
136.0  1.0    
4.0    146.0

//We verify the accuracy margin resulting in 98% accuracy

metrics.accuracy 
//res5: Double = 0.9825783972125436