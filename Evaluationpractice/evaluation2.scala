/*Instrucciones: Desarrollar las siguientes instrucciones en Spark con el lenguaje de
programación Scala, utilizando solo la documentación de la librería de
Machine Learning Mllib de Spark y Google.*/

/*1. Cargar en un dataframe de la fuente de datos Iris.csv que se encuentra en
https://github.com/jcromerohdz/iris, elaborar la limpieza de datos necesaria para
ser procesado por el siguiente algoritmo (Importante, esta limpieza debe ser por
medio de un script de Scala en Spark).
a. Utilice la librería Mllib de Spark el algoritmo de Machine Learning
Multilayer Perceptron Classifier*/

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Pipeline
//Importar librerias utilizables con el modelo multilayer
//Y los indexadores para clasificar

//Importar datos desde el csv, limpieza de datos, cambiamos var data a iris por el infer schema
var iris = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("iris.csv")
//Otra opcion de infer schema es: val df = data.withColumn("sepal_length",$"sepal_length".cast("double")).withColumn("sepal_width",$"sepal_width".cast("double")).withColumn("petal_length", $"petal_length".cast("double")).withColumn("petal_width", $"petal_width".cast("double"))

//2. ¿Cuáles son los nombres de las columnas?
iris.show()

//3. ¿Cómo es el esquema?
iris.printSchema()

//4. Imprime las primeras 5 columnas.
iris.show(5)

//5. Usa el método describe () para aprender más sobre los datos del DataFrame.
iris.describe().show()

//6. Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
val assembler = new VectorAssembler().setInputCols(Array("sepal_length", "sepal_width", "petal_length", "petal_width")).setOutputCol("features")
//Total las caracteristicas
val features = assembler.transform(iris)
//Agarra un dato categorico y lo vuelve numerico
val indexerLabel = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(features)

val indexerFeatures = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4)

val Array(training, test) = features.randomSplit(Array(0.7, 0.3), seed = 12345)

//7. Construya el modelo de clasificación y explique su arquitectura.
val layers = Array[Int](4, 5, 4, 3)

val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(128).setSeed(1234).setMaxIter(100)

val converterLabel = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(indexerLabel.labels)

val pipeline = new Pipeline().setStages(Array(indexerLabel, indexerFeatures, trainer, converterLabel))

val model = pipeline.fit(training)

//8. Imprima los resultados del modelo y de sus observaciones.
val results = model.transform(test)

val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")

val accuracy = evaluator.evaluate(results)

println("Error = " + (1.0 - accuracy))