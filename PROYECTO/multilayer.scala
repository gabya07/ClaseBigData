import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Pipeline
//Importar librerias utilizables con el modelo multilayer
//Y los indexadores para clasificar

//Importar datos desde el csv, limpieza de datos, cambiamos var data a bank-full por el infer schema
val bank = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("bank-full.csv")

//Nombres de las columnas, Ver cómo es el esquema y describe () para aprender más sobre los datos del DataFrame.
bank.printSchema()
bank.show(0)
bank.describe().show()

//Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
//val assembler = new VectorAssembler().setInputCols(Array("age","job","marital","education","default","balance","housing","loan","contact","day","month","duration","campaign","pdays","previous","poutcome")).setOutputCol("features")
val assembler = new VectorAssembler().setInputCols(Array("age")).setOutputCol("features")

val features = assembler.transform(bank)
//Agarra un dato categorico y lo vuelve numerico
//val indexerLabel = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(features)

//val indexerFeatures = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(16)

//val Array(training, test) = features.randomSplit(Array(0.7, 0.3), seed = 12345)

//Construir el modelo de clasificación y explique su arquitectura.
//val layers = Array[Int](16, 5, 3, 2)
//val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(128).setSeed(1234).setMaxIter(100)
//val converterLabel = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(indexerLabel.labels)
//val pipeline = new Pipeline().setStages(Array(indexerLabel, indexerFeatures, trainer, converterLabel))
//val model = pipeline.fit(training)

//Resultados del modelo y de sus observaciones.
//val results = model.transform(test)
//val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
//val accuracy = evaluator.evaluate(results)
//println("Error = " + (1.0 - accuracy))

