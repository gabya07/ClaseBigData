import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.feature.OneHotEncoder
import org.apache.spark.ml.Pipeline

//Importar librerias utilizables con el modelo multilayer
//Y los indexadores para clasificar

//Importar datos desde el csv, limpieza de datos, cambiamos var data a bank-full por el infer schema
val bank = spark.read.option("header", "true").option("inferSchema", "true").format("csv").load("bank-full.csv")

//Nombres de las columnas, Ver cómo es el esquema y describe () para aprender más sobre los datos del DataFrame.
bank.na.drop().show(false)
bank.show(0)
bank.describe().show()
bank.printSchema()

// Deal with Categorical Columns
// Transform string type columns to string indexer 
val jobIndexer = new StringIndexer().setInputCol("job").setOutputCol("jobIndex")
val educationIndexer = new StringIndexer().setInputCol("education").setOutputCol("educationIndex")
val maritalIndexer = new StringIndexer().setInputCol("marital").setOutputCol("maritalIndex")
val defaultIndexer = new StringIndexer().setInputCol("default").setOutputCol("defaultIndex")
val housingIndexer = new StringIndexer().setInputCol("housing").setOutputCol("housingIndex")
val loanIndexer = new StringIndexer().setInputCol("loan").setOutputCol("loanIndex")
val contactIndexer = new StringIndexer().setInputCol("contact").setOutputCol("contactIndex")
val monthIndexer = new StringIndexer().setInputCol("month").setOutputCol("monthIndex")
val poutcomeIndexer = new StringIndexer().setInputCol("poutcome").setOutputCol("poutcomeIndex")

// Transform string type columns to string indexer 
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
//Haga la transformación pertinente para los datos categóricos los cuales serán nuestras etiquetas a clasificar.
//val assembler = new VectorAssembler().setInputCols(Array("age","job","marital","education","default","balance","housing","loan","contact","day","month","duration","campaign","pdays","previous","poutcome")).setOutputCol("features")
val assembler = new VectorAssembler().setInputCols(Array("age","jobVec","maritalVec","educationVec","defaultVec","balance","housingVec","loanVec","contactVec","day","monthVec","duration","campaign","pdays","previous","poutcomeVec")).setOutputCol("features")

val features = assembler.transform(bank)
//Agarra un dato categorico y lo vuelve numerico
val indexerLabel = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(features)

val indexerFeatures = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(6)

val Array(training, test) = features.randomSplit(Array(0.7, 0.3), seed = 12345)

//Construir el modelo de clasificación y explique su arquitectura.
val layers = Array[Int](16, 5, 3, 2)
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(40000).setSeed(12345).setMaxIter(100)
val converterLabel = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(indexerLabel.labels)
val pipeline = new Pipeline().setStages(Array(indexerLabel, indexerFeatures, trainer, converterLabel))
val model = pipeline.fit(training)

//Resultados del modelo y de sus observaciones.
val results = model.transform(test)
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(results)
println("Error = " + (1.0 - accuracy))

