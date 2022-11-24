//Random forest classifier
//The following examples load a dataset in LibSVM format, split it into training and test sets, 
//train on the first dataset, and then evaluate on the held-out test set.

//Importar las librerias
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{RandomForestClassificationModel, RandomForestClassifier}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}

// Importar el archivo .txt localizado en la carpeta raiz.
val data = spark.read.format("libsvm").load("sample_libsvm_data.txt")

// Se añaden Etiquetas de índice, agregando metadatos a la columna de etiquetas.
// El fit ajusta todo el conjunto de datos para incluir todas las etiquetas en el índice.
val labelIndexer = new StringIndexer()
  .setInputCol("label")
  .setOutputCol("indexedLabel")
  .fit(data)
//labelIndexer.type = strIdx_96e7fc205cc8

// Indexar lascaracterísticas categóricas.
// Establecer maxCategories para que las entidades con > 4 valores distintos se traten como continuas.
val featureIndexer = new VectorIndexer()
  .setInputCol("features")
  .setOutputCol("indexedFeatures")
  .setMaxCategories(4)
  .fit(data)
//featureIndexer.type = vecIdx_30dd78843ccd
//Total de numFeatures=692

//Dividir los datos en conjuntos de entrenamiento y prueba (30% retenido para prueba).
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
//trainingData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]
//testData: org.apache.spark.sql.Dataset[org.apache.spark.sql.Row] = [label: double, features: vector]

// Entrenar un modelo RandomForest.
val rf = new RandomForestClassifier()
  .setLabelCol("indexedLabel")
  .setFeaturesCol("indexedFeatures")
  .setNumTrees(10)
//rf: org.apache.spark.ml.classification.RandomForestClassifier = rfc_bfb0350fdef2

// Convierta las etiquetas indexadas de nuevo en etiquetas originales.
val labelConverter = new IndexToString()
  .setInputCol("prediction")
  .setOutputCol("predictedLabel")
  .setLabels(labelIndexer.labelsArray(0))
//labelConverter.type = idxToStr_97adc61eae91

// Indizadores de cadena y bosque en un Pipeline.
val pipeline = new Pipeline()
  .setStages(Array(labelIndexer, featureIndexer, rf, labelConverter))
//pipeline.type = pipeline_173770e50539

// Correr Train Model
val model = pipeline.fit(trainingData)

// Hacer predicciones.
val predictions = model.transform(testData)

// Seleccionar filas de ejemplo para mostrar.
predictions.select("predictedLabel", "label", "features").show(5)
//MulticlassClassificationEvaluator: uid=mcEval_fc77ad617985, metricName=f1, metricLabel=0.0, beta=1.0, eps=1.0E-15

// Seleccione (predicción, etiqueta verdadera) y calcule el error de prueba.
val evaluator = new MulticlassClassificationEvaluator()
  .setLabelCol("indexedLabel")
  .setPredictionCol("prediction")
  .setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"Test Error = ${(1.0 - accuracy)}")
val rfModel = model.stages(2).asInstanceOf[RandomForestClassificationModel]
println(s"Learned classification forest model:\n ${rfModel.toDebugString}")
/* //rfModel: org.apache.spark.ml.classification.RandomForestClassificationModel = RandomForestClassificationModel: uid=rfc_bfb0350fdef2, numTrees=10, numClasses=2, numFeatures=692
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