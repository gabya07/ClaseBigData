# Clase Big Data
**Teamwork: Gabriela Aguilar, Humberto Chavez y Ricardo Chavez**

## Content
- [Clase Big Data](#clase-big-data)
  * [Proyecto](#proyecto)
  * [Log-Regression](#log-regression)
  * [Multilayer-Perceptron ](#Multilayer-Perceptron)

## UNIT 3

## Proyecto


## Log-Regression

We import the necessary libraries to implement the logistic regression model in spark
```sh
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
```
We avoid warnings
```sh
Logger.getLogger("org").setLevel(Level.ERROR)

```
We start the session in spark and read with Spark the data of "bank-full.csv" in CSV format, which we store in Val "data"
```sh
val spark = SparkSession.builder().getOrCreate()

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("bank-full.csv")
```
We review the data of the data set to see what we face with show, describe and inferchema, then identify that the column "y" will be our main search pattern and rename the column "YIndex" to "label".
```sh
data.show(0)
+---+---+-------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
|age|job|marital|education|default|balance|housing|loan|contact|day|month|duration|campaign|pdays|previous|poutcome|  y|
+---+---+-------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
+---+---+-------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
data.describe().show()
+-------+------------------+-------+--------+---------+-------+------------------+-------+-----+--------+-----------------+-----+------------------+------------------+------------------+------------------+--------+-----+
|summary|               age|    job| marital|education|default|           balance|housing| loan| contact|              day|month|          duration|          campaign|   
          pdays|          previous|poutcome|    y|
+-------+------------------+-------+--------+---------+-------+------------------+-------+-----+--------+-----------------+-----+------------------+------------------+------------------+------------------+--------+-----+
|  count|             45211|  45211|   45211|    45211|  45211|             45211|  45211|45211|   45211|            45211|45211|             45211|             45211|   
          45211|             45211|   45211|45211|
|   mean| 40.93621021432837|   null|    null|     null|   null|1362.2720576850766|   null| null|    null|15.80641879188693| null| 258.1630797814691| 2.763840658246887| 40.19782796222158|0.5803233726305546|    null| null|
| stddev|10.618762040975398|   null|    null|     null|   null|3044.7658291685216|   null| null|    null| 8.32247615304459| null|257.52781226517124|3.0980208832791805|100.12874599059822| 2.303441044931215|    null| null|
|    min|                18| admin.|divorced|  primary|     no|             -8019|     no|   no|cellular|                1|  apr|                 0|                 1|   
             -1|                 0| failure|   no|
|    max|                95|unknown|  single|  unknown|    yes|            102127|    yes|  yes| unknown|               31|  sep|              4918|                63|   
            871|               275| unknown|  yes|
+-------+------------------+-------+--------+---------+-------+------------------+-------+-----+--------+-----------------+-----+------------------+------------------+------------------+------------------+--------+-----+
val yIndexer = new StringIndexer().setInputCol("y").setOutputCol("yIndex")
val data1 = yIndexer.fit(data).transform(data)
data1.printSchema()
root
 |-- age: integer (nullable = true)
 |-- job: string (nullable = true)
 |-- marital: string (nullable = true)
 |-- education: string (nullable = true)
 |-- default: string (nullable = true)
 |-- balance: integer (nullable = true)
 |-- housing: string (nullable = true)
 |-- loan: string (nullable = true)
 |-- contact: string (nullable = true)
 |-- day: integer (nullable = true)
 |-- month: string (nullable = true)
 |-- duration: integer (nullable = true)
 |-- campaign: integer (nullable = true)
 |-- pdays: integer (nullable = true)
 |-- previous: integer (nullable = true)
 |-- poutcome: string (nullable = true)
 |-- y: string (nullable = true)
 |-- yIndex: double (nullable = false)
val logregdataall = (data1.select(data1("yIndex").as("label"), $"age",$"job", $"marital",
                    $"education", $"default", $"balance", $"housing", $"loan", $"contact", $"day", $"month", $"duration", $"campaign", $"pdays", $"previous", $"poutcome"))
```
We use drop in the data set
```sh
val logregdata = logregdataall.na.drop()
```
Transforming string into numerical values
```sh
val jobIndexer = new StringIndexer().setInputCol("job").setOutputCol("jobIndex")
val educationIndexer = new StringIndexer().setInputCol("education").setOutputCol("educationIndex")
val maritalIndexer = new StringIndexer().setInputCol("marital").setOutputCol("maritalIndex")
val defaultIndexer = new StringIndexer().setInputCol("default").setOutputCol("defaultIndex")
val housingIndexer = new StringIndexer().setInputCol("housing").setOutputCol("housingIndex")
val loanIndexer = new StringIndexer().setInputCol("loan").setOutputCol("loanIndex")
val contactIndexer = new StringIndexer().setInputCol("contact").setOutputCol("contactIndex")
val monthIndexer = new StringIndexer().setInputCol("month").setOutputCol("monthIndex")
val poutcomeIndexer = new StringIndexer().setInputCol("poutcome").setOutputCol("poutcomeIndex")
```
Convert numeric values to One Hot Encoding 0 - 1
```sh
val jobEncoder = new OneHotEncoder().setInputCol("jobIndex").setOutputCol("jobVec")
val educationEncoder = new OneHotEncoder().setInputCol("educationIndex").setOutputCol("educationVec")
val maritalEncoder = new OneHotEncoder().setInputCol("maritalIndex").setOutputCol("maritalVec")
val defaultEncoder = new OneHotEncoder().setInputCol("defaultIndex").setOutputCol("defaultVec")
val housingEncoder = new OneHotEncoder().setInputCol("housingIndex").setOutputCol("housingVec")
val loanEncoder = new OneHotEncoder().setInputCol("loanIndex").setOutputCol("loanVec")
val contactEncoder = new OneHotEncoder().setInputCol("contactIndex").setOutputCol("contactVec")
val monthEncoder = new OneHotEncoder().setInputCol("monthIndex").setOutputCol("monthVec")
val poutcomeEncoder = new OneHotEncoder().setInputCol("poutcomeIndex").setOutputCol("poutcomeVec")
// Assemble everything together to be ("label","features") format

```
We define the input data of the model
```sh
val assembler = new VectorAssembler().setInputCols(Array("age","jobVec","maritalVec","educationVec","defaultVec","balance","housingVec","loanVec","contactVec","day","monthVec","duration","campaign","pdays","previous","poutcomeVec")).setOutputCol("features")

```
The values are selected for the training and testing of the model, in addition a seed of randomness is sown to have more certainty and the results
```sh

val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 45354)
```
We create a "LogisticRegression" object named "Ir"
```sh
val lr = new LogisticRegression()
```
We create a new "Pipeline" object with the elements of the one hot encoder, store the data frame in "pipeline", We adjust "fit" of the pipeline for "training", we store the data frame in val "model".
```sh
val pipeline = new Pipeline().setStages(Array(jobIndexer,maritalIndexer,educationIndexer,defaultIndexer,housingIndexer,loanIndexer,contactIndexer,monthIndexer,poutcomeIndexer,jobEncoder,maritalEncoder,educationEncoder,defaultEncoder,housingEncoder,loanEncoder,contactEncoder,monthEncoder,poutcomeEncoder,assembler,lr))

val model = pipeline.fit(training)
```
We store in "results" the test set using transform
```sh
val results = model.transform(test)
```
We convert the test results into RDD, store the data frame in val "predictionAndLabels"
```sh
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)
```
We print confusion matrix and metrics of the accuracy
```sh
println("Confusion matrix:")
println(metrics.confusionMatrix)
//11602.0  287.0  
//1011.0   581.0
metrics.accuracy
//Double = 0.9037163415176915

```


## Multilayer-Perceptron

We import the necessary libraries to implement the multilayer perceptron model in spark
```sh
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Pipeline
```
We read with Spark the data of "bank-full.csv" in CSV format, which we store in Val "bank"
```sh
val bank = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("bank-full.csv")
```
Column names, See what the schema looks like, and describe () to learn more about the DataFrame data.
```sh
bank.na.drop().show(false)
+---+------------+--------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
|age|job         |marital |education|default|balance|housing|loan|contact|day|month|duration|campaign|pdays|previous|poutcome|y  |
+---+------------+--------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
|58 |management  |married |tertiary |no     |2143   |yes    |no  |unknown|5  |may  |261     |1       |-1   |0       |unknown |no |
|44 |technician  |single  |secondary|no     |29     |yes    |no  |unknown|5  |may  |151     |1       |-1   |0       |unknown |no |
|33 |entrepreneur|married |secondary|no     |2      |yes    |yes |unknown|5  |may  |76      |1       |-1   |0       |unknown |no |
|47 |blue-collar |married |unknown  |no     |1506   |yes    |no  |unknown|5  |may  |92      |1       |-1   |0       |unknown |no |
|33 |unknown     |single  |unknown  |no     |1      |no     |no  |unknown|5  |may  |198     |1       |-1   |0       |unknown |no |
|35 |management  |married |tertiary |no     |231    |yes    |no  |unknown|5  |may  |139     |1       |-1   |0       |unknown |no |
|28 |management  |single  |tertiary |no     |447    |yes    |yes |unknown|5  |may  |217     |1       |-1   |0       |unknown |no |
|42 |entrepreneur|divorced|tertiary |yes    |2      |yes    |no  |unknown|5  |may  |380     |1       |-1   |0       |unknown |no |
|58 |retired     |married |primary  |no     |121    |yes    |no  |unknown|5  |may  |50      |1       |-1   |0       |unknown |no |
|43 |technician  |single  |secondary|no     |593    |yes    |no  |unknown|5  |may  |55      |1       |-1   |0       |unknown |no |
|41 |admin.      |divorced|secondary|no     |270    |yes    |no  |unknown|5  |may  |222     |1       |-1   |0       |unknown |no |
|29 |admin.      |single  |secondary|no     |390    |yes    |no  |unknown|5  |may  |137     |1       |-1   |0       |unknown |no |
|53 |technician  |married |secondary|no     |6      |yes    |no  |unknown|5  |may  |517     |1       |-1   |0       |unknown |no |
|58 |technician  |married |unknown  |no     |71     |yes    |no  |unknown|5  |may  |71      |1       |-1   |0       |unknown |no |
|57 |services    |married |secondary|no     |162    |yes    |no  |unknown|5  |may  |174     |1       |-1   |0       |unknown |no |
|51 |retired     |married |primary  |no     |229    |yes    |no  |unknown|5  |may  |353     |1       |-1   |0       |unknown |no |
|45 |admin.      |single  |unknown  |no     |13     |yes    |no  |unknown|5  |may  |98      |1       |-1   |0       |unknown |no |
|57 |blue-collar |married |primary  |no     |52     |yes    |no  |unknown|5  |may  |38      |1       |-1   |0       |unknown |no |
|60 |retired     |married |primary  |no     |60     |yes    |no  |unknown|5  |may  |219     |1       |-1   |0       |unknown |no |
|33 |services    |married |secondary|no     |0      |yes    |no  |unknown|5  |may  |54      |1       |-1   |0       |unknown |no |
+---+------------+--------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
bank.show(0)
+---+---+-------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
|age|job|marital|education|default|balance|housing|loan|contact|day|month|duration|campaign|pdays|previous|poutcome|  y|
+---+---+-------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
+---+---+-------+---------+-------+-------+-------+----+-------+---+-----+--------+--------+-----+--------+--------+---+
bank.describe().show()
+-------+------------------+-------+--------+---------+-------+------------------+-------+-----+--------+-----------------+-----+------------------+------------------+------------------+------------------+--------+-----+
|summary|               age|    job| marital|education|default|           balance|housing| loan| contact|              day|month|          duration|          campaign|   
          pdays|          previous|poutcome|    y|
+-------+------------------+-------+--------+---------+-------+------------------+-------+-----+--------+-----------------+-----+------------------+------------------+------------------+------------------+--------+-----+
|  count|             45211|  45211|   45211|    45211|  45211|             45211|  45211|45211|   45211|            45211|45211|             45211|             45211|   
          45211|             45211|   45211|45211|
|   mean| 40.93621021432837|   null|    null|     null|   null|1362.2720576850766|   null| null|    null|15.80641879188693| null| 258.1630797814691| 2.763840658246887| 40.19782796222158|0.5803233726305546|    null| null|
| stddev|10.618762040975398|   null|    null|     null|   null|3044.7658291685216|   null| null|    null| 8.32247615304459| null|257.52781226517124|3.0980208832791805|100.12874599059822| 2.303441044931215|    null| null|
|    min|                18| admin.|divorced|  primary|     no|             -8019|     no|   no|cellular|                1|  apr|                 0|                 1|   
             -1|                 0| failure|   no|
|    max|                95|unknown|  single|  unknown|    yes|            102127|    yes|  yes| unknown|               31|  sep|              4918|                63|   
            871|               275| unknown|  yes|
+-------+------------------+-------+--------+---------+-------+------------------+-------+-----+--------+-----------------+-----+------------------+------------------+------------------+------------------+--------+-----+
bank.printSchema()

|-- age: integer (nullable = true)
 |-- job: string (nullable = true)
 |-- marital: string (nullable = true)
 |-- education: string (nullable = true)
 |-- default: string (nullable = true)
 |-- balance: integer (nullable = true)
 |-- housing: string (nullable = true)
 |-- loan: string (nullable = true)
 |-- contact: string (nullable = true)
 |-- day: integer (nullable = true)
 |-- month: string (nullable = true)
 |-- duration: integer (nullable = true)
 |-- campaign: integer (nullable = true)
 |-- pdays: integer (nullable = true)
 |-- previous: integer (nullable = true)
 |-- poutcome: string (nullable = true)
 |-- y: string (nullable = true)
```

Transforming string into numerical values
```sh
val jobIndexer = new StringIndexer().setInputCol("job").setOutputCol("jobIndex")
val educationIndexer = new StringIndexer().setInputCol("education").setOutputCol("educationIndex")
val maritalIndexer = new StringIndexer().setInputCol("marital").setOutputCol("maritalIndex")
val defaultIndexer = new StringIndexer().setInputCol("default").setOutputCol("defaultIndex")
val housingIndexer = new StringIndexer().setInputCol("housing").setOutputCol("housingIndex")
val loanIndexer = new StringIndexer().setInputCol("loan").setOutputCol("loanIndex")
val contactIndexer = new StringIndexer().setInputCol("contact").setOutputCol("contactIndex")
val monthIndexer = new StringIndexer().setInputCol("month").setOutputCol("monthIndex")
val poutcomeIndexer = new StringIndexer().setInputCol("poutcome").setOutputCol("poutcomeIndex")
```
Fit and transform columns
```sh
val indexed = educationIndexer.fit(bank).transform(bank)
val indexed1 = jobIndexer.fit(indexed).transform(indexed)
val indexed2 = maritalIndexer.fit(indexed1).transform(indexed1)
val indexed3 = defaultIndexer.fit(indexed2).transform(indexed2)
val indexed4 = housingIndexer.fit(indexed3).transform(indexed3)
val indexed5 = loanIndexer.fit(indexed4).transform(indexed4)
val indexed6 = contactIndexer.fit(indexed5).transform(indexed5)
val indexed7 = monthIndexer.fit(indexed6).transform(indexed6)
val indexed8 = poutcomeIndexer.fit(indexed7).transform(indexed7)
```
We delete unnecessary columns
```sh
indexed8.drop("job","marital","education","default","housing","loan","contact","month", "poutcome").show(false)
```
We define the input data of the model
```sh
// Assemble everything together to be ("label","features") format
val assembler = new VectorAssembler().setInputCols(Array("age","jobIndex","maritalIndex","educationIndex","defaultIndex","balance","housingIndex","loanIndex","previous","poutcomeIndex")).setOutputCol("features")
val features = assembler.transform(indexed8)
```
We take a categorical data and turn it numerical, We set the parameters of training and test
```sh
val indexerLabel = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(features)
val indexerFeatures = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(10)
val Array(training, test) = features.randomSplit(Array(0.7, 0.3), seed = 12327)
```
We will specify entry data for the neural network and prepare multilayer perceptron model, store the data frame in "pipeline", We adjust "fit" of the pipeline for "training", we store the data frame in val "model".
```sh
val layers = Array[Int](10,8,6,2)
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(12345).setSeed(1234).setMaxIter(2123)
val converterLabel = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(indexerLabel.labels)
val pipeline = new Pipeline().setStages(Array(indexerLabel, indexerFeatures, trainer, converterLabel))
val model = pipeline.fit(training)
```
We store in "results" the test set using transform, We use the evaluator to calculate accuracy.
```sh
val results = model.transform(test)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(results)
//accuracy: Double = 0.8811034075822393
println("Error = " + (1.0 - accuracy))
//Error = 0.11889659241776074
```

