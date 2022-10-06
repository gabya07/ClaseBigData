//Instrucciones: Responder las siguientes preguntas con Spark DataFrames y Scala utilizando el “CSV” Netflix_2011_2016.csv que se encuentra en la carpeta de spark-dataframes.
//1. Iniciar una sesión simple de spark.
//cd Evaluationpractice
//spark-shell
//:load evaluation1.scala

//2. Cargue el archivo Netflix Stock CSV en dataframe llamado df, haga que Spark infiera los tipos de datos.
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

//3. ¿Cuáles son los nombres de las columnas?
df.columns

//4. ¿Cómo es el esquema?
df.printSchema()

//5. Imprime las primeras 5 renglones.
df.head(5)
df.show(5)

//6. Usa el método describe () para aprender sobre el DataFrame.
df.describe().show()

//7. Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la
//relación que existe entre el precio de la columna “High” frente a la columna
//“Volumen” de acciones negociadas por un día. Hint - es una operación
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))

//8. ¿Qué día tuvo el pico más alto en la columna “Open”?
df.select(max("Open")).show()

//9. ¿Cuál es el significado de la columna Cerrar “Close” en el contexto de información
//financiera, explíquelo no hay que codificar nada?
// La columna close indica el valor en el que Cierra ese dia

//10. ¿Cuál es el máximo y mínimo de la columna “Volumen”?
df.select(max("Volume")).show()
df.select(min("Volume")).show()

//11. Con Sintaxis Scala/Spark $ conteste lo siguiente:
//a) ¿Cuántos días fue la columna “Close” inferior a $ 600?
df.filter($"Close"<600).count()

//b) ¿Qué porcentaje del tiempo fue la columna “High” mayor que $ 500?
(df.filter($"High">500).count()*100.00)/df.count()

//c) ¿Cuál es la correlación de Pearson entre columna “High” y la columna “Volumen”?
df.select(corr("High","Volume")).show()

//d) ¿Cuál es el máximo de la columna “High” por año?
val df3 = df.withColumn("Year", year(df("Date")))
val dfprom = df3.groupBy("Year").max()
dfprom.select($"Year", $"max(High)").show()

//e) ¿Cuál es el promedio de la columna “Close” para cada mes del calendario?
val df4 = df.withColumn("Year", year(df("Date"))).withColumn("Month", month(df("Date")))
val df5 = df4.select(concat($"Year", lit("-"), $"Month").as("M/Y"),$"Close")
val dfavgs = df5.groupBy("M/Y").mean().sort("M/Y").show(72)
