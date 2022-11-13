// Import LinearRegression

import org.apache.spark.ml.regression.LinearRegression

// Opcional: Utilice el siguiente codigo para configurar errores

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Inicie una simple Sesion Spark

import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Utilice Spark para el archivo csv Clean-Ecommerce .

val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("Clean-Ecommerce.csv")

// Imprima el schema en el DataFrame.

/*data.printSchema()
root
 |-- Email: string (nullable = true)
 |-- Avatar: string (nullable = true)
 |-- Avg Session Length: double (nullable = true)
 |-- Time on App: double (nullable = true)
 |-- Time on Website: double (nullable = true)
 |-- Length of Memberip: double (nullable = true)
 |-- Yearly Amount Spent: double (nullable = true)

*/
// Imprima un renglon de ejemplo del DataFrane.

data.head(1)
Array[org.apache.spark.sql.Row] = Array([mstephenson@fernandez.com,Violet,34.49726772511229,12.65565114916675,39.57766801952616,4.0826206329529615,587.9510539684005])

//////////////////////////////////////////////////////
//// Configure el DataFrame para Machine Learning ////
//////////////////////////////////////////////////////

// Transforme el data frame para que tome la forma de
// ("label","features")
// Importe VectorAssembler y Vectors

import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Renombre la columna Yearly Amount Spent como "label"
// Tambien de los datos tome solo la columa numerica 
// Deje todo esto como un nuevo DataFrame que se llame df

val df = data.select(data("Yearly Amount Spent").as("label"),$"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Memberip")

// Que el objeto assembler convierta los valores de entrada a un vector
// Utilice el objeto VectorAssembler para convertir la columnas de entradas del df
// a una sola columna de salida de un arreglo llamado  "features"
// Configure las columnas de entrada de donde se supone que leemos los valores.
// Llamar a esto nuevo assambler.

val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Memberip")).setOutputCol("features")

// Utilice el assembler para transform nuestro DataFrame a dos columnas: label and features

val output = assembler.transform(df).select($"label", $"features")
output.ow()

// Crear un objeto para modelo de regresion linea.

val lr = new LinearRegression()
val lr = new LinearRegression().setMaxIter(100).setRegParam(0.3).setElasticNetParam(0.8)

// Ajuste el modelo para los datos y llame a este modelo lrModelo

val lrModelo = lr.fit(output)

// Imprima the  coefficients y intercept para la regresion lineal

println(s"Coefficients: ${lrModelo.coefficients} Intercept: ${lrModelo.intercept}")
Coefficients: [25.474157405011358,38.459296789654964,0.19719156783415023,61.30177443550338] 
Intercept: -1030.1338974058647

// Resuma el modelo sobre el conjunto de entrenamiento imprima la salida de algunas metricas!
// Utilize metodo .summary de nuestro  modelo para crear un objeto
// llamado trainingSummary

val trainingSummary = lrModelo.summary

// Muestre los valores de residuals, el RMSE, el MSE, y tambien el R^2 .

trainingSummary.residuals.ow()
/*+-------------------+
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
res14: Double = 9.936872481716364*/

trainingSummary.predictions.ow()
/*
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
*/
trainingSummary.r2 
//res16: Double = 0.9842724662033581

// END!