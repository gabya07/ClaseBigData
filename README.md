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
```sh
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.log4j._
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.ml.Pipeline
import org.apache.spark.mllib.evaluation.MulticlassMetrics
```
```sh
Logger.getLogger("org").setLevel(Level.ERROR)

```

```sh
val spark = SparkSession.builder().getOrCreate()

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("bank-full.csv")
```
```sh
data.show(0)
data.describe().show()
val yIndexer = new StringIndexer().setInputCol("y").setOutputCol("yIndex")
val data1 = yIndexer.fit(data).transform(data)
data1.printSchema()
val logregdataall = (data1.select(data1("yIndex").as("label"), $"age",$"job", $"marital",
                    $"education", $"default", $"balance", $"housing", $"loan", $"contact", $"day", $"month", $"duration", $"campaign", $"pdays", $"previous", $"poutcome"))
```
```sh
val logregdata = logregdataall.na.drop()
```
```sh
// Convertir strings a valores numericos - Transforming string into numerical values
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

```sh
// Convertir los valores numericos a One Hot Encoding 0 - 1
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
```sh
//Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
val assembler = new VectorAssembler().setInputCols(Array("age","jobVec","maritalVec","educationVec","defaultVec","balance","housingVec","loanVec","contactVec","day","monthVec","duration","campaign","pdays","previous","poutcomeVec")).setOutputCol("features")

```
```sh

val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 45354)
```
```sh
val lr = new LogisticRegression()
```
```sh
val pipeline = new Pipeline().setStages(Array(jobIndexer,maritalIndexer,educationIndexer,defaultIndexer,housingIndexer,loanIndexer,contactIndexer,monthIndexer,poutcomeIndexer,jobEncoder,maritalEncoder,educationEncoder,defaultEncoder,housingEncoder,loanEncoder,contactEncoder,monthEncoder,poutcomeEncoder,assembler,lr))

val model = pipeline.fit(training)
```

```sh
val results = model.transform(test)
```
```sh
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)
```
```sh
// Matriz de confusion
println("Confusion matrix:")
println(metrics.confusionMatrix)

metrics.accuracy
```


## Multilayer-Perceptron


```sh
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Pipeline


//Importar librerias utilizables con el modelo multilayer
//Y los indexadores para clasificar
```

```sh
//Importar datos desde el csv, limpieza de datos, cambiamos var data a bank-full por el infer schema
val bank = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("bank-full.csv")

```

```sh
//Nombres de las columnas, Ver cómo es el esquema y describe () para aprender más sobre los datos del DataFrame.
bank.na.drop().show(false)
bank.show(0)
bank.describe().show()
bank.printSchema()
```


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

```sh
//delete columns
indexed8.drop("job","marital","education","default","housing","loan","contact","month", "poutcome").show(false)
```

```sh
// Assemble everything together to be ("label","features") format
//Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
val assembler = new VectorAssembler().setInputCols(Array("age","jobIndex","maritalIndex","educationIndex","defaultIndex","balance","housingIndex","loanIndex","previous","poutcomeIndex")).setOutputCol("features")

val features = assembler.transform(indexed8)
```

```sh
// Agarra un dato categorico y lo vuelve numerico
val indexerLabel = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(features)
val indexerFeatures = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(10)
val Array(training, test) = features.randomSplit(Array(0.7, 0.3), seed = 12327)
```

```sh
// //Construir el modelo de clasificación y explique su arquitectura.
val layers = Array[Int](10,8,6,2)
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(12345).setSeed(1234).setMaxIter(2123)
val converterLabel = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(indexerLabel.labels)
val pipeline = new Pipeline().setStages(Array(indexerLabel, indexerFeatures, trainer, converterLabel))
val model = pipeline.fit(training)
```

```sh
// //Resultados del modelo y de sus observaciones.
val results = model.transform(test)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(results)
println("Error = " + (1.0 - accuracy))
```

