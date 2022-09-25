import org.apache.spark.sql.SparkSession

// 1 Adding a Column CarData CSV
//withcolumn adds a new column to existing dataframe with its value 
val df = spark.read.option("header", "true").option("inferSchema","true")csv("CarData")

df.withColumn("Seats", lit(7)).show(1)
// +---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
//|Car_Model| mpg|cyl| disp| hp|drat|  wt| qsec| vs| am|gear|carb|Seats|
//+---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
//|Mazda RX4|21.0|  6|160.0|110| 3.9|2.62|16.46|  0|  1|   4|   4|    7|
//+---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+

// 2 Using select and summary to pull  data from specific columns un the dataframe 

df.select("Car_model", "Cyl").summary("min","max", "count").show()
/* +-------+-----------+---+
|summary|  Car_model|Cyl|
+-------+-----------+---+
|    min|AMC Javelin|  4|
|    max| Volvo 142E|  8|
|  count|         32| 32|
+-------+-----------+---+ */


//3 Drop   using drop to delete single  and multiple columns from the DATAFRAME
df.drop("Car_Model", "wt", "mpg").show(1)
/* +----+---+-----+---+----+----+-----+---+---+----+----+
| mpg|cyl| disp| hp|drat|  wt| qsec| vs| am|gear|carb|
+----+---+-----+---+----+----+-----+---+---+----+----+
|21.0|  6|160.0|110| 3.9|2.62|16.46|  0|  1|   4|   4|
+----+---+-----+---+----+----+-----+---+---+----+----+ */

df.drop("Car_Model", "wt", "mpg").show(1)
/* +---+-----+---+----+-----+---+---+----+----+
|cyl| disp| hp|drat| qsec| vs| am|gear|carb|
+---+-----+---+----+-----+---+---+----+----+
|  6|160.0|110| 3.9|16.46|  0|  1|   4|   4|
+---+-----+---+----+-----+---+---+----+----+ */

//4 Using Fill and FILL NA to fill null / empty valus in a dataframe
/* +---+-------+--------+-------------------+-----+----------+
| id|zipcode|    type|               city|state|population|
+---+-------+--------+-------------------+-----+----------+
|  1|    704|STANDARD|               null|   PR|     30100|
|  2|    704|    null|PASEO COSTA DEL SUR|   PR|      null|
|  3|    709|    null|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|  UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|STANDARD|               null|   TX|      null|
+---+-------+--------+-------------------+-----+----------+ */

df.na.fill(value=0).show()  
//#this will fill any null INT  with a any INT value we enter, in this case 0.
/* +---+-------+--------+-------------------+-----+----------+
| id|zipcode|    type|               city|state|population|
+---+-------+--------+-------------------+-----+----------+
|  1|    704|STANDARD|               null|   PR|     30100|
|  2|    704|    null|PASEO COSTA DEL SUR|   PR|         0|
|  3|    709|    null|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|  UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|STANDARD|               null|   TX|         0|
+---+-------+--------+-------------------+-----+----------+ */
df.na.fill(value="PRACTICA-BIGDATA").show() 
//#This will fill any null string with the given value#

/* +---+-------+----------------+-------------------+-----+----------+
| id|zipcode|            type|               city|state|population|
+---+-------+----------------+-------------------+-----+----------+
|  1|    704|        STANDARD|   PRACTICA-BIGDATA|   PR|     30100|
|  2|    704|PRACTICA-BIGDATA|PASEO COSTA DEL SUR|   PR|      null|
|  3|    709|PRACTICA-BIGDATA|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|          UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|        STANDARD|   PRACTICA-BIGDATA|   TX|      null|
+---+-------+----------------+-------------------+-----+----------+. */


//Gaby Starts//
val spark = SparkSession.builder
    .master("local[1]")
    .appName("SparkByExamples.com")
    .getOrCreate()

val data = Seq(("James","Smith","USA","CA"),
  ("Michael","Rose","USA","NY"),
  ("Robert","Williams","USA","CA"),
  ("Maria","Jones","USA","FL")
  )
val columns = Seq("firstname","lastname","country","state")
import spark.implicits._
val df = data.toDF(columns:_*)
df.show(false)

//1. Show all columns from DataFrame
df.select("*").show()
val columnsAll=df.columns.map(m=>col(m))
df.select(columnsAll:_*).show()
df.select(columns.map(m=>col(m)):_*).show()

val listCols= List("lastname","country")
df.select(listCols.map(m=>col(m)):_*).show()

//2. Select first 2 columns.
df.select(df.columns.slice(0,2).map(m=>col(m)):_*).show()

//3. Show columns by regular expression
  df.select(df.colRegex("`^.*name*`")).show()

  df.select(df.columns.filter(f=>f.startsWith("first")).map(m=>col(m)):_*).show(3)
  df.select(df.columns.filter(f=>f.endsWith("name")).map(m=>col(m)):_*).show(3)

//4. Drop the column
df.drop(df("state")).printSchema()

//5. Rename column
df.withColumnRenamed("state","estado").printSchema()
