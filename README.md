# Clase Big Data
**Teamwork: Gabriela Aguilar, Humberto Chavez y Ricardo Chavez**

## Content
- [Clase Big Data](#clase-big-data)
  * [Practice 1](#practice-1)
  * [Practice 2](#practice-2)
  * [Practice 3](#practice-3)
  * [Practice 4](#practice-4)

## UNIT 2

## Practice 1
// Import LinearRegression
```sh
import org.apache.spark.ml.regression.LinearRegression
```
// Opcional: Utilice el siguiente codigo para configurar errores
```sh
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```
// Inicie una simple Sesion Spark
```sh
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
```
// Utilice Spark para el archivo csv Clean-Ecommerce .
```sh
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Clean-Ecommerce.csv")
```
// Imprima el schema en el DataFrame.
```sh
data.printSchema()
root
 |-- Email: string (nullable = true)
 |-- Avatar: string (nullable = true)
 |-- Avg Session Length: double (nullable = true)
 |-- Time on App: double (nullable = true)
 |-- Time on Website: double (nullable = true)
 |-- Length of Membership: double (nullable = true)
 |-- Yearly Amount Spent: double (nullable = true)
```

// Imprima un renglon de ejemplo del DataFrane.
```sh
data.head(1)
Array[org.apache.spark.sql.Row] = Array([mstephenson@fernandez.com,Violet,34.49726772511229,12.65565114916675,39.57766801952616,4.0826206329529615,587.9510539684005])
```


//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

// Transforme el data frame para que tome la forma de
// ("label","features")
// Importe VectorAssembler y Vectors
```sh
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```
// Renombre la columna Yearly Amount Spent como "label"
// Tambien de los datos tome solo la columa numerica 
// Deje todo esto como un nuevo DataFrame que se llame df
```sh
val df = data.select(data("Yearly Amount Spent").as("label"),$"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership")
```
// Que el objeto assembler convierta los valores de entrada a un vector
// Utilice el objeto VectorAssembler para convertir la columnas de entradas del df
// a una sola columna de salida de un arreglo llamado  "features"
// Configure las columnas de entrada de donde se supone que leemos los valores.
// Llamar a esto nuevo assambler.
```sh
val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Membership")).setOutputCol("features")
```
// Utilice el assembler para transform nuestro DataFrame a dos columnas: label and features
```sh
val output = assembler.transform(df).select($"label", $"features")
output.show()
```
// Crear un objeto para modelo de regresion linea.
```sh
val lr = new LinearRegression()
val lr = new LinearRegression().setMaxIter(100).setRegParam(0.3).setElasticNetParam(0.8)
```
// Ajuste el modelo para los datos y llame a este modelo lrModelo
```sh
val lrModelo = lr.fit(output)
```
// Imprima the  coefficients y intercept para la regresion lineal
```sh
println(s"Coefficients: ${lrModelo.coefficients} Intercept: ${lrModelo.intercept}")
Coefficients: [25.474157405011358,38.459296789654964,0.19719156783415023,61.30177443550338] 
Intercept: -1030.1338974058647
```
// Resuma el modelo sobre el conjunto de entrenamiento imprima la salida de algunas metricas!
// Utilize metodo .summary de nuestro  modelo para crear un objeto
// llamado trainingSummary
```sh
val trainingSummary = lrModelo.summary
```
// Muestre los valores de residuals, el RMSE, el MSE, y tambien el R^2 .
```sh
trainingSummary.residuals.show()
+-------------------+
|          residuals|
+-------------------+
|  -5.50759186520952|
|  11.12275760571157|
|-17.677299700379308|
| 12.001539481691566|
|   8.40673608599127|
|-1.7071850687621009|
|  4.407543134611046|
|  -8.21023420279505|
|  11.63046122427977|
|-14.248859903860648|
|-15.728731592824829|
|   8.94078467752297|
|   9.46480498142455|
| 12.310257125476369|
|  9.698393299108488|
|  9.919111551842093|
| 19.207040331100643|
|  -3.95572532841436|
|-3.9614657184429802|
|  9.754952407513201|
+-------------------+

trainingSummary.rootMeanSquaredError
res14: Double = 9.936872481716364

trainingSummary.predictions.show()
+------------------+--------------------+------------------+
|             label|            features|        prediction|
+------------------+--------------------+------------------+
| 587.9510539684005|[34.4972677251122...|   593.45864583361|
| 392.2049334443264|[31.9262720263601...|381.08217583861483|
|487.54750486747207|[33.0009147556426...| 505.2248045678514|
| 581.8523440352177|[34.3055566297555...| 569.8508045535261|
| 599.4060920457634|[33.3306725236463...| 590.9993559597722|
|  637.102447915074|[33.8710378793419...| 638.8096329838361|
| 521.5721747578274|[32.0215955013870...| 517.1646316232163|
| 549.9041461052942|[32.7391429383803...| 558.1143803080893|
| 570.2004089636196|[33.9877728956856...| 558.5699477393398|
| 427.1993848953282|[31.9365486184489...|441.44824479918884|
| 492.6060127179966|[33.9925727749537...| 508.3347443108214|
| 522.3374046069357|[33.8793608248049...| 513.3966199294127|
| 408.6403510726275|[29.5324289670579...|399.17554609120293|
| 573.4158673313865|[33.1903340437226...| 561.1056102059101|
| 470.4527333009554|[32.3879758531538...| 460.7543400018469|
| 461.7807421962299|[30.7377203726281...| 451.8616306443878|
|457.84769594494855|[32.1253868972878...| 438.6406556138479|
|407.70454754954415|[32.3388993230671...| 411.6602728779585|
| 452.3156754800354|[32.1878120459321...| 456.2771411984784|
|  605.061038804892|[32.6178560628234...| 595.3060863973787|
+------------------+--------------------+------------------+

trainingSummary.r2 
res16: Double = 0.9842724662033581
```
// END!

## Practice 2

////////////////////////
/// Tome los datos //////
//////////////////////

We import the library needed to use Logistic Regression in Spark
```sh
import org.apache.spark.ml.classification.LogisticRegression
```
We import the library needed to start a session in Spark
```sh
import org.apache.spark.sql.SparkSession 
```
Optional to avoid warnings
```sh
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR) 
```
We start the session in spark
```sh
val spark = SparkSession.builder().getOrCreate() 
```
We read with Spark the data of "Advertising.csv" in CSV format, which we store in Val "data"
```sh
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("advertising.csv") 
```
We print the schema loaded in "data"
```sh
data.printSchema() 
root
 |-- Daily Time Spent on Site: double (nullable = true)
 |-- Age: integer (nullable = true)
 |-- Area Income: double (nullable = true)
 |-- Daily Internet Usage: double (nullable = true)
 |-- Ad Topic Line: string (nullable = true)
 |-- City: string (nullable = true)
 |-- Male: integer (nullable = true)
 |-- Country: string (nullable = true)
 |-- Timestamp: timestamp (nullable = true)
 |-- Clicked on Ad: integer (nullable = true)
```
///////////////////////////////
/// Despliegue los datos ////
/////////////////////////////

We print the first line of the table
```sh
data.head(1) 
res1: Array[org.apache.spark.sql.Row] = Array([68.95,35,61833.9,256.09,Cloned 5thgeneration orchestration,Wrightburgh,0,Tunisia,2016-03-27 00:53:11.0,0])
```
With the arrangement we print the header of each column with the first registered value
```sh
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}
```

/////////////////////////////////////////////////////
//// Preparar el DataFrame para Machine Learning ////
////////////////////////////////////////////////////

Create a new column called "Hour" that will contain the "Hour of the click", store the data frame in val "timedata"
```sh
val timedata = data.withColumn("Hour",hour(data("Timestamp"))) 
```
Rename the column "Clicked on Ad" to "label"
```sh
val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male") 
```
We import the "VectorAssembler" library to be able to use its functions
```sh
import org.apache.spark.ml.feature.VectorAssembler 
```
We import the "Vectors" library to be able to use its functions
```sh
import org.apache.spark.ml.linalg.Vectors 
```
We create a new "VectorAssembler" object for the "features", store the data frame in val "assembler"
```sh
val assembler = (new VectorAssembler()
                  .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male"))
                  .setOutputCol("features")) 
```

We use randomSplit to divide the training into 70 and the test into 30
```sh
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)
```

///////////////////////////////
// Configure un Pipeline ///////
/////////////////////////////

We import the "Pipeline" library to be able to use its functions
```sh
import org.apache.spark.ml.Pipeline 
```
We create a "LogisticRegression" object named "Ir"
```sh
val lr = new LogisticRegression() 
```
We create a new "Pipeline" object with the elements "assembler, Go", store the data frame in "pipeline" val
```sh
val pipeline = new Pipeline().setStages(Array(assembler, lr))
```
We adjust "fit" of the pipeline for "training", we store the data frame in val "model"
```sh
val model = pipeline.fit(training) 
```
We store in "results" the test set using transform
```sh
val results = model.transform(test) 
```

////////////////////////////////////
//// Evaluacion del modelo ////////
//////////////////////////////////

We import the "MulticlassMetrics" library to use its evaluation functions
```sh
import org.apache.spark.mllib.evaluation.MulticlassMetrics 
```
We convert the test results into RDD, store the data frame in val "predictionAndLabels"
```sh
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd 
```
Start the MulticlassMetrics object called "predictionAndLabels", store in the new data frame "metrics"
```sh
val metrics = new MulticlassMetrics(predictionAndLabels) 
```
We print "Confusion Matrix" giving us the result a TP = 136, FP = 1, FN = 4, TN = 146, which indicates that it is a good model since there are high rates of PT and TN and low rates of FP and FN
```sh
println("Confusion matrix:")
println(metrics.confusionMatrix) 
136.0  1.0    
4.0    146.0
```
We verify the accuracy margin resulting in 98% accuracy
```sh
metrics.accuracy 
res5: Double = 0.9825783972125436
```
## Practice 3 Random forest classifier
Importamos librerias
```sh
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

import org.apache.log4j._                        //Configuracion
Logger.getLogger("org").setLevel(Level.ERROR)  
```
Inciamos sesion Sprk
```sh
val spark = SparkSession.builder().getOrCreate()  
```
// se carga el archivo de datos en formato libsvm con extension txt desde la carpeta main
```sh
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```
Se identifican features con data categorica y se indexan  fit el conjunto de data
```sh
val labelIndexer = new StringIndexer()
  .setInputCol("label")
  .setOutputCol("indexedLabel")
  .fit(data)
```
// Se identifican features con data categorica y se indexan  fit ajusta los datos
```sh
val featureIndexer = new VectorIndexer()
  .setInputCol("features")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(4) 
  .fit(data)
```
Se parte la data en set de entrenamiento y de prueba, dejando 70% de data para entrenamiento del modelo y 30% para pruebas se agrega el seed para darle mas randomness 
```sh
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3),seed=1234)
```

Se ejecuta el entrenmianto del modelo de Decision tree en variable dt
```sh
val dt = new DecisionTreeClassifier()
  .setLabelCol("indexedLabel")
  .setFeaturesCol("indexedFeatures")
```

```sh
```

```sh
```

## Practice 4 Random forest classifier
Importar las librerias
```sh
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
```
Importar el archivo .txt localizado en la carpeta raiz.
```sh
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")
```
Se añaden Etiquetas de índice, agregando metadatos a la columna de etiquetas.
El fit ajusta todo el conjunto de datos para incluir todas las etiquetas en el índice.
```sh
val labelIndexer = new StringIndexer()
  .setInputCol("label")
  .setOutputCol("indexedLabel")
  .fit(data)
labelIndexer.type = strIdx_96e7fc205cc8
```
Indexar lascaracterísticas categóricas y establecer maxCategories para que las entidades con > 4 valores distintos se traten como continuas.
```sh
val featureIndexer = new VectorIndexer()
  .setInputCol("features")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(4)
  .fit(data)
featureIndexer.type = vecIdx_30dd78843ccd
Total de numFeatures=692
```
Dividir los datos en conjuntos de entrenamiento y prueba (30% retenido para prueba).
```sh
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
trainingData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
testData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
```
Entrenar un modelo RandomForest.
```sh
val rf = new RandomForestClassifier()
  .setLabelCol("indexedLabel")
  .setFeaturesCol("indexedFeatures")
  .setNumTrees(10)
rf: org.apache.spark.ml.classification.RandomForestClassifier = rfc_bfb0350fdef2
```
Convierta las etiquetas indexadas de nuevo en etiquetas originales.
```sh
val labelConverter = new IndexToString()
  .setInputCol("prediction")
  .setOutputCol("predictedLabel")
  .setLabels(labelIndexer.labelsArray(0))
labelConverter.type = idxToStr_97adc61eae91
```
Indizadores de cadena y bosque en un Pipeline.
```sh
val pipeline = new Pipeline()
  .setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
pipeline.type = pipeline_173770e50539
```
Correr Train Model y hacer predicciones
```sh
val model = pipeline.fit(trainingData)
val predictions = model.transform(testData)
```
Seleccionar filas de ejemplo para mostrar.
```sh
predictions.select("predictedLabel", "label", "features").show(5)
MulticlassClassificationEvaluator: uid=mcEval_fc77ad617985, metricName=f1, metricLabel=0.0, beta=1.0, eps=1.0E-15
```
Seleccione (predicción, etiqueta verdadera) y calcule el error de prueba.
```sh
val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("indexedLabel")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")

rfModel: org.apache.spark.ml.classification.RandomForestClassificationModel = RandomForestClassificationModel: uid=rfc_bfb0350fdef2, numTrees=10, numClasses=2, numFeatures=692
Learned classification forest model:
 RandomForestClassificationModel: uid=rfc_bfb0350fdef2, numTrees=10, numClasses=2, numFeatures=692
  Tree 0 (weight 1.0):
    If (feature 455 <= 25.5)
     Predict: 0.0
    Else (feature 455 > 25.5)
     Predict: 1.0
  Tree 1 (weight 1.0):
    If (feature 236 <= 0.5)
     If (feature 633 <= 1.5)
      Predict: 0.0
     Else (feature 633 > 1.5)
      Predict: 1.0
    Else (feature 236 > 0.5)
     If (feature 455 <= 11.5)
      Predict: 0.0
     Else (feature 455 > 11.5)
      Predict: 1.0
  Tree 2 (weight 1.0):
    If (feature 414 <= 8.5)
     If (feature 300 <= 32.0)
      If (feature 179 <= 114.0)
       Predict: 0.0
      Else (feature 179 > 114.0)
       Predict: 1.0
     Else (feature 300 > 32.0)
      Predict: 1.0
    Else (feature 414 > 8.5)
     Predict: 1.0
  Tree 3 (weight 1.0):
    If (feature 469 <= 9.5)
     If (feature 356 <= 16.0)
      If (feature 358 <= 10.5)
       If (feature 522 <= 253.5)
        Predict: 0.0
       Else (feature 522 > 253.5)
        Predict: 1.0
      Else (feature 358 > 10.5)
       Predict: 1.0
     Else (feature 356 > 16.0)
      Predict: 1.0
    Else (feature 469 > 9.5)
     Predict: 1.0
  Tree 4 (weight 1.0):
    If (feature 433 <= 66.5)
     If (feature 489 <= 1.5)
      Predict: 1.0
     Else (feature 489 > 1.5)
      If (feature 401 <= 31.5)
       Predict: 0.0
      Else (feature 401 > 31.5)
       Predict: 1.0
    Else (feature 433 > 66.5)
     Predict: 0.0
  Tree 5 (weight 1.0):
    If (feature 378 <= 73.0)
     If (feature 211 <= 253.5)
      Predict: 1.0
     Else (feature 211 > 253.5)
      If (feature 381 <= 2.0)
       Predict: 1.0
      Else (feature 381 > 2.0)
       Predict: 0.0
    Else (feature 378 > 73.0)
     If (feature 490 <= 27.5)
      Predict: 1.0
     Else (feature 490 > 27.5)
      Predict: 0.0
  Tree 6 (weight 1.0):
    If (feature 511 <= 15.0)
     If (feature 299 <= 214.5)
      Predict: 0.0
     Else (feature 299 > 214.5)
      Predict: 1.0
    Else (feature 511 > 15.0)
     Predict: 1.0
  Tree 7 (weight 1.0):
    If (feature 317 <= 9.5)
     If (feature 461 <= 66.5)
      Predict: 1.0
     Else (feature 461 > 66.5)
      Predict: 0.0
    Else (feature 317 > 9.5)
     If (feature 511 <= 15.0)
      Predict: 0.0
     Else (feature 511 > 15.0)
      Predict: 1.0
  Tree 8 (weight 1.0):
    If (feature 385 <= 28.0)
     If (feature 287 <= 1.0)
      If (feature 406 <= 9.5)
       Predict: 1.0
      Else (feature 406 > 9.5)
       Predict: 0.0
     Else (feature 287 > 1.0)
      Predict: 1.0
    Else (feature 385 > 28.0)
     Predict: 1.0
  Tree 9 (weight 1.0):
    If (feature 567 <= 8.0)
     If (feature 406 <= 9.5)
      Predict: 1.0
     Else (feature 406 > 9.5)
      Predict: 0.0
    Else (feature 567 > 8.0)
     If (feature 209 <= 6.5)
      If (feature 184 <= 254.5)
       Predict: 0.0
      Else (feature 184 > 254.5)
       Predict: 1.0
     Else (feature 209 > 6.5)
      Predict: 1.0 */
      ```