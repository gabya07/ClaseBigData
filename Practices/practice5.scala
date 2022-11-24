// Importamos las librerias necesarias para la practica

import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

// Descargamos y cargamos el txt "sample_multiclass_classification_data.txt, cargamos los datos en formato "libsvm" y almacenamos en el dt "data"
val data = spark.read.format("libsvm").load("sample_multiclass_classification_data.txt")

// Asignamos un 60,40 en el array del randon split, almacenado en el dt splits, dividimos los datos en entrenamiento y prueba
val splits = data.randomSplit(Array(0.6, 0.4), seed = 1234L)
val train = splits(0)
val test = splits(1)

// Especificamos los datos de entrada de la red neuronal 4 para caracteristicas, dos intermedias de 5 y 4, y 3 como salida en este caso las clases.
// almacenamos en el dt layers
val layers = Array[Int](4, 5, 4, 3)

// Creamos el entrenador utilizando la libreria MultilayerPerceptronClassifier y asignamos los valores a sus parametros, todo esto lo guardamos en el dt trainer
val trainer = new MultilayerPerceptronClassifier()
  .setLayers(layers)
  .setBlockSize(128)
  .setSeed(1234L)
  .setMaxIter(100)

// Guardamos en el dt model el entrenamiento del modelo
val model = trainer.fit(train)

// Precisamos los calculos necesarios para poder ejecutar las pruebas, 
val result = model.transform(test)
val predictionAndLabels = result.select("prediction", "label")
val evaluator = new MulticlassClassificationEvaluator()
  .setMetricName("accuracy")
// imprimimos la precision de la prueba de acuerdo al evaluador
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
// output = Test set accuracy = 0.9523809523809523
//en este caso el resultado nos arrojo una precision del 95% lo que nos indica que es un modelo relativamente bueno.