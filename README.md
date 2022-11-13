# Clase Big Data
**Teamwork: Gabriela Aguilar, Humberto Chavez y Ricardo Chavez**

## Content
- [Clase Big Data](#clase-big-data)
  * [Practice 1](#practice-1)
  * [Practice 2](#practice-2)
  * [Practice 3](#practice-3)
  * [Practice 4](#practice-4)
  * [Practice 5](#practice-5)
  * [Evaluation Practice](#EvaluationPractice-1)

## Practice 1
Develop an algorithm that calculates the **radius of a circle**
```sh
val Diameter: Int = 20
val Radio: Int = Diameter / 2
```
**Output**
```sh
Radio: Int = 10
```
Develop an algorithm that tells me if a number is **odd**
```sh
var num = 7
if (num % 2 == 0) {
    printf("No es número primo")
} else {
    printf("Si es número primo")
}
```
**Output**
```sh
Si es numero primo
```
Given the variable bird = "tweet", use string interpolation to print **"I'm writing a tweet"**
```sh
var Bird: String = "tweet"
var PrintTweet = s"Estoy escribiendo un ${Bird}"
```
**Output**
```sh
PrintTweet: String = Estoy escribiendo un tweet
```
Given the message variable = "Hello Luke, I'm your father!" use slice to extract the sequence **"Luke"**
```sh
val msn = "Hola Luke yo soy tu padre!"
msn slice (5,9)
```
**Output**
```sh
res1: String = Luke
```

What is the difference between **value** and a **variable** in scala? 
```sh
The values ​​of "val" are immutable, unlike "var" where the values ​​change.
```

Given the tuple (2,4,5,1,2,3,3.1416,23) returns the number **3.1416**
```sh
val tup = (2,4,5,1,2,3,3.1416,23) 
tup._7
```
**Output**
```sh
res2: Double = 3.1416
```
## Practice 2

Create a list called "list" with the elements **"red", "white", "black"**

```sh
val lista = List("rojo","blanco","negro")
```
**Output**
```sh
lista: List[String] = List(rojo, blanco, negro)
```
Add 5 more items to **"list" "green" ,"yellow", "blue", "orange", "pearl"**

```sh
val lista = List("rojo","blanco","negro","verde","amarillo","azul","naranja","perla")
```
**Output**
```sh
lista: List[String] = List(rojo, blanco, negro, verde, amarillo, azul, naranja, perla)
```
Fetch items from "list" **"green", "yellow", "blue"**
```sh
lista slice (3,6)
```
**Output**
```sh
res3: List[String] = List(verde, amarillo, azul)
```
Create an array of numbers in the range **1-1000** in steps of 5 at a time
```sh
Array.range(0, 1000, 5)
```
**Output**
```sh
res4: Array[Int] = Array(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 135, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240, 245, 250, 255, 260, 265, 270, 275, 280, 285, 290, 295, 300, 305, 310, 315, 320, 325, 330, 335, 340, 345, 350, 355, 360, 365, 370, 375, 380, 385, 390, 395, 400, 405, 410, 415, 420, 425, 430, 435, 440, 445, 450, 455, 460, 465, 470, 475, 480, 485, 490, 495, 500, 505, 510, 515, 520, 525, 530, 535, 540, 545, 550, 555, 560, 565, 570, 575, 580, 585, 590, 595, 600, 605, 610, 615, 620, 625, 630, 635, 640, 645, 650, 655, 660, 665, 670, 675, 680, 685, 690, 695, 700, 
705, 710, 715, 720, 725, 730, 735, 740, 745, 750, 755, 760, 765, 770, 775, 780, 785, 790,...
```
What are the unique elements of the list **List(1,3,3,4,6,7,3,7)** use conversion to sets
```sh
val s = Set(1,3,3,4,6,7,3,7)
```
**Output**
```sh
s: scala.collection.immutable.Set[Int] = Set(1, 6, 7, 3, 4)
```
Create a mutable map named names containing the following **"Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"**
**Print all** the keys on the map
Add the following value to the map **("Miguel", 23)**

```sh
val mutmap = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))
mutmap.keys
mutmap += ("Miguel" -> 23)
```
**Output**
```sh
res6: mutmap.type = Map(Susana -> 27, Ana -> 23, Miguel -> 23, Luis -> 24, Jose -> 20)
```
## Practice 3

Exercise
This function takes a list, **validates and prints if the numbers within the list are ODD or EVEN**, 
at the very end it will print DONE
```sh
def listEvens(list:List[Int]): String ={ 
    for(n <- list){                     
        if(n%2==0){                     
            println(s"$n is even")      
        }else{                          
            println(s"$n is odd")       
        }
    }
    return "Done"                       
}

val l = List(1,2,3,4,5,6,7,8)           
val l2 = List(4,3,22,55,7,8)            
listEvens(l)                            
listEvens(l2)                           
```
**Output**
```sh
Run output
l: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)
l2: List[Int] = List(4, 3, 22, 55, 7, 8)
1 is odd
2 is even
3 is odd
4 is even
5 is odd
6 is even
7 is odd
8 is even
res0: String = Done
4 is even
3 is odd
22 is even
55 is odd
7 is odd
8 is even
res1: String = Done
```

Excercise
Description: The **"afortunado" function receives an integer type list and returns an integer data type**
the variable res = 0 is initialized, a cycle for will run the list, in the for a if it tells us that when in the list there is a number equal to 7 to the variable res(accumulator) 14 will be added, and otherwise with a else a res(accumulator) the number will be added within the list,  finally in the return we return the final result of res (accumulator).
**Finally we print the result of the "afortunado" function with the list "af", which gives us 29, the result of the sum 14 + 14 + 1 within the for.**
```sh
def afortunado(list:List[Int]): Int={
    var res=0
    for(n <- list){
        if(n==7){
            res = res + 14
        }else{
            res = res + n
        }
    }
    return res
}

val af= List(1,7,7)
println(afortunado(af))
```
**Output**
```sh
 29
```
Exercise
**This Function get a list and compare by itself;**
First gets SEGUNDA as the sum of the same list (B1=6) and Primera as (0) on the FOR, 
PRIMERA is adding the list item and SEGUNDA is subtracting the list item, 
in each cases the final result is compared between and if is the same prints TRUE
```sh
def balance(list:List[Int]): Boolean={
    var primera = 0
    var segunda = 0

    segunda = list.sum

    for(i <- Range(0,list.length)){
        primera = primera + list(i)
        segunda = segunda - list(i)

        if(primera == segunda){
            return true
        }
    }
    return false 
}

val bl = List(3,2,1)
val bl2 = List(2,3,3,2)
val bl3 = List(10,30,90)

balance(bl)
balance(bl2)
balance(bl3)
```

**Output**
```sh
res5: Boolean = true
res6: Boolean = true
res7: Boolean = false
```
Exercise
**This function validates the word with the same word in a reverse way**
And if is the same with the reverse it prints **True**
```sh
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}
val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))
```
**Output**
```sh
true
true
false
```


## Practice 4
Exercise
```sh
def fibonacci_algoritmo_1(n: Int): Int = {
    if      (n < 2) n
    else    fibonacci_algoritmo_1(n-1) + fibonacci_algoritmo_1(n-2)
  }
fibonacci_algoritmo_1(10)

```
**Output**
```sh
55
```
Exercise
```sh
def fibonacci_algoritmo_3(n: Int): Int = {
    var a = 0
    var b = 1
    var c = 0
    for (i <- 0 to n){
      c = b + a
      a = b
      b = c
    }
    a
  }
fibonacci_algoritmo_3(10)
```
**output**
```sh
89
```
Exercise
```sh
def fib4(n: Int): Int = {
    var a = 0
    var b = 1
    for (k <- 0 to n){
      b = b + a
      a = b - a
      
    }
    return b
  }
fib4(15)
```
**Output**
```sh
1597
```
Exercise 
```sh
def fib(n: Int): List[Int] = {
    var vector = scala.collection.mutable.ListBuffer[Int](0,1)
    while (vector.length < n+1) {
      val k = vector(vector.length - 1) + vector(vector.length - 2)
      vector += k
    }  
    vector.toList
    return vector.toList
  }
fib(20)
```
**Output**
```sh
List[Int] = List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765)
```

## Practice 5


**Adding a Column CarData CSV**
**withcolumn** adds a new column to existing dataframe with its value 
```sh
val df = spark.read.option("header", "true").option("inferSchema","true")csv("CarData")
df.withColumn("Seats", lit(7)).show(1)
```
**Output**
```sh
 +---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
|Car_Model| mpg|cyl| disp| hp|drat|  wt| qsec| vs| am|gear|carb|Seats|
+---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
|Mazda RX4|21.0|  6|160.0|110| 3.9|2.62|16.46|  0|  1|   4|   4|    7|
+---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
```
**Using select and summary to pull  data from specific columns un the dataframe**
```sh
df.select("Car_model", "Cyl").summary("min","max", "count").show()
```
**Output**
```sh
+-------+-----------+---+
|summary|  Car_model|Cyl|
+-------+-----------+---+
|    min|AMC Javelin|  4|
|    max| Volvo 142E|  8|
|  count|         32| 32|
+-------+-----------+---+
```

**Drop**   using drop to delete single  and multiple columns from the DATAFRAME
```sh
df.drop("Car_Model", "wt", "mpg").show(1)
```
**Output**
```sh
+----+---+-----+---+----+----+-----+---+---+----+----+
| mpg|cyl| disp| hp|drat|  wt| qsec| vs| am|gear|carb|
+----+---+-----+---+----+----+-----+---+---+----+----+
|21.0|  6|160.0|110| 3.9|2.62|16.46|  0|  1|   4|   4|
+----+---+-----+---+----+----+-----+---+---+----+----+
```
```sh
df.drop("Car_Model", "wt", "mpg").show(1)
+---+-----+---+----+-----+---+---+----+----+
|cyl| disp| hp|drat| qsec| vs| am|gear|carb|
+---+-----+---+----+-----+---+---+----+----+
|  6|160.0|110| 3.9|16.46|  0|  1|   4|   4|
+---+-----+---+----+-----+---+---+----+----+
```


Using **Fill** and FILL NA to fill null / empty valus in a dataframe
Current DF status
```sh
+---+-------+--------+-------------------+-----+----------+
| id|zipcode|    type|               city|state|population|
+---+-------+--------+-------------------+-----+----------+
|  1|    704|STANDARD|               null|   PR|     30100|
|  2|    704|    null|PASEO COSTA DEL SUR|   PR|      null|
|  3|    709|    null|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|  UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|STANDARD|               null|   TX|      null|
+---+-------+--------+-------------------+-----+----------+
```
this will fill any null INT  with a any INT value we enter, in this case 0
```sh
df.na.fill(value=0).show()  
```

**Output**
```sh
+---+-------+--------+-------------------+-----+----------+
| id|zipcode|    type|               city|state|population|
+---+-------+--------+-------------------+-----+----------+
|  1|    704|STANDARD|               null|   PR|     30100|
|  2|    704|    null|PASEO COSTA DEL SUR|   PR|         0|
|  3|    709|    null|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|  UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|STANDARD|               null|   TX|         0|
+---+-------+--------+-------------------+-----+----------+
```

This will fill any null string with the given value
```sh
df.na.fill(value="PRACTICA-BIGDATA").show() 
```
**Output**
```sh

+---+-------+----------------+-------------------+-----+----------+
| id|zipcode|            type|               city|state|population|
+---+-------+----------------+-------------------+-----+----------+
|  1|    704|        STANDARD|   PRACTICA-BIGDATA|   PR|     30100|
|  2|    704|PRACTICA-BIGDATA|PASEO COSTA DEL SUR|   PR|      null|
|  3|    709|PRACTICA-BIGDATA|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|          UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|        STANDARD|   PRACTICA-BIGDATA|   TX|      null|
+---+-------+----------------+-------------------+-----+----------+.
```

From the "CitiGroup2006_2008" table, we **calculate the maximum number of High and the minimum number of Low grouped by Volume.**
```sh
df.groupBy($"Volume").agg(Map("High" -> "max","Low" -> "min")).show()
```

**Output**
```sh
+--------+---------+--------+
|  Volume|max(High)|min(Low)|
+--------+---------+--------+
| 1480300|    505.3|   491.9|
| 4007266|     68.0|    65.3|
| 2080880|    507.1|   499.2|
| 2235160|    566.7|   551.2|
|18768112|    225.0|   212.3|
| 9059744|    122.8|   110.5|
| 1207780|    453.8|   450.0|
| 1645100|    492.4|   487.0|
|22087515|    283.6|   267.1|
|21529341|     83.6|    71.7|
| 1662830|    551.9|   544.1|
| 9339288|    184.2|   174.3|
|16765271|     98.1|    87.5|
| 1865640|    507.1|   497.0|
| 1744910|    544.9|   539.5|
| 1656790|    469.4|   463.1|
| 2341860|    486.0|   474.7|
| 1783110|    512.4|   503.1|
| 3472498|    470.2|   460.1|
|  979570|    480.2|   475.3|
+--------+---------+--------+
```
ias  From the "CitiGroup2006_2008" table, we calculate average of Open and the average of Close grouped by Date.
```sh
df.groupBy($"Date").agg(Map("Open" -> "avg","Close" -> "avg")).show()
```
**Output**
```sh
+-------------------+---------+----------+
|               Date|avg(Open)|avg(Close)|
+-------------------+---------+----------+
|2006-12-18 00:00:00|    543.8|     554.4|
|2007-07-13 00:00:00|    525.5|     525.2|
|2007-11-28 00:00:00|    310.2|     322.9|
|2007-02-22 00:00:00|    537.5|     535.9|
|2008-06-19 00:00:00|    205.0|     201.7|
|2006-01-09 00:00:00|    486.0|     483.9|
|2006-06-12 00:00:00|    499.0|     493.3|
|2007-08-21 00:00:00|    481.5|     480.6|
|2007-06-07 00:00:00|    532.5|     525.2|
|2006-03-08 00:00:00|    463.5|     466.8|
|2008-06-06 00:00:00|    210.4|     200.6|
|2006-01-11 00:00:00|    495.8|     489.8|
|2006-02-01 00:00:00|    465.9|     463.3|
|2006-10-18 00:00:00|    501.9|     501.9|
|2006-12-26 00:00:00|    546.8|     551.2|
|2007-07-19 00:00:00|    516.0|     511.3|
|2006-09-12 00:00:00|    490.0|     489.9|
|2006-12-11 00:00:00|    518.5|     528.8|
|2008-09-15 00:00:00|    163.9|     152.4|
|2006-12-05 00:00:00|    498.9|     505.1|
+-------------------+---------+----------+
```

 Compute the **average for all numeric columns** rolled up by Date.
 ```sh

df.rollup("Date").avg().show()
```
**Output**
```sh
+-------------------+---------+---------+--------+----------+-----------+
|               Date|avg(Open)|avg(High)|avg(Low)|avg(Close)|avg(Volume)|
+-------------------+---------+---------+--------+----------+-----------+
|2006-10-31 00:00:00|    501.2|    504.6|   501.1|     501.6|  1813900.0|
|2007-10-10 00:00:00|    473.0|    473.8|   467.7|     471.4|  2914515.0|
|2007-12-10 00:00:00|    344.4|    352.3|   343.5|     347.7|  5731204.0|
|2007-12-19 00:00:00|    305.2|    311.1|   300.6|     302.1|  7530323.0|
|2008-11-17 00:00:00|     93.6|     98.1|    87.5|      88.9|1.6765271E7|
|2008-12-15 00:00:00|     77.4|     77.8|    72.3|      74.0|1.0157328E7|
|2006-04-05 00:00:00|    483.0|    484.7|   481.3|     482.6|  1276340.0|
|2007-04-03 00:00:00|    511.7|    516.6|   511.6|     514.1|  2145281.0|
|2008-01-15 00:00:00|    282.6|    283.6|   267.1|     269.4|2.2087515E7|
|2007-08-13 00:00:00|    477.4|    479.1|   464.4|     465.4|  3450110.0|
|2008-10-20 00:00:00|    151.8|    154.0|   146.9|     150.9|1.0724806E7|
|2006-10-23 00:00:00|    498.0|    507.4|   497.9|     506.2|  2093940.0|
|2006-12-26 00:00:00|    546.8|    551.9|   545.2|     551.2|   880650.0|
|2007-12-13 00:00:00|    311.0|    313.2|   302.7|     310.1|  9558791.0|
|2006-06-09 00:00:00|    499.5|    501.0|   497.0|     497.7|  1303300.0|
|2006-06-30 00:00:00|    489.5|    489.5|   482.4|     482.5|  1991780.0|
|2006-08-09 00:00:00|    486.9|    488.7|   476.9|     478.3|  1084320.0|
|2007-07-31 00:00:00|    475.7|    482.6|   465.0|     465.7|  4679730.0|
|2007-09-26 00:00:00|    463.6|    470.2|   460.1|     465.5|  3472498.0|
|2008-04-07 00:00:00|    248.5|    251.9|   243.9|     246.0|  8928018.0|
+-------------------+---------+---------+--------+----------+-----------+
```

 We order the table "CitiGroup2006_2008" sorted by Open **descending and Close ascending**
```sh
df.sort($"Open".desc, $"Close".asc).show()
```

**Output**
```sh
+-------------------+-----+-----+-----+-----+-------+
|               Date| Open| High|  Low|Close| Volume|
+-------------------+-----+-----+-----+-----+-------+
|2006-12-28 00:00:00|566.0|570.0|555.5|558.8|1530700|
|2006-12-29 00:00:00|558.5|560.9|555.5|557.0|1103860|
|2007-01-03 00:00:00|556.6|562.8|547.2|552.5|2282110|
|2007-05-29 00:00:00|554.2|554.2|546.2|549.1|1600091|
|2007-01-04 00:00:00|552.5|561.5|547.2|550.6|1658680|
|2007-05-24 00:00:00|552.4|555.2|547.4|549.3|2243121|
|2007-05-21 00:00:00|551.8|552.0|547.4|548.4|2331728|
|2007-01-22 00:00:00|551.7|555.3|546.6|546.8|2808290|
|2006-12-21 00:00:00|551.7|554.4|546.5|547.6|1626330|
|2007-02-01 00:00:00|551.3|551.6|543.0|547.3|1875525|
|2006-12-19 00:00:00|551.3|552.3|545.4|552.3|2640640|
|2007-05-23 00:00:00|551.2|555.3|548.5|550.1|1952183|
|2006-12-20 00:00:00|551.2|552.9|547.8|551.6|2034190|
|2006-12-27 00:00:00|551.2|566.7|551.2|564.1|2235160|
|2007-05-31 00:00:00|551.1|552.0|543.1|544.9|1956605|
|2007-01-09 00:00:00|550.1|551.5|541.9|545.7|1965810|
|2007-01-17 00:00:00|550.0|550.8|542.8|543.9|1406520|
|2007-01-05 00:00:00|550.0|550.5|544.6|547.7|1317880|
|2007-05-18 00:00:00|550.0|555.5|547.4|550.0|2102519|
|2006-12-22 00:00:00|549.5|551.0|544.6|545.5| 964770|
+-------------------+-----+-----+-----+-----+-------+
```

From the "CitiGroup2006_2008" table, we calculate the **maximum number of Volume** grouped by Date 
```sh

df.groupBy(df.col("Date")).agg(max("Volume")).show()
```
**Output**
```sh
+-------------------+-----------+
|               Date|max(Volume)|
+-------------------+-----------+
|2006-12-18 00:00:00|    4549940|
|2007-07-13 00:00:00|    2035436|
|2007-11-28 00:00:00|   13887433|
|2007-02-22 00:00:00|    1909300|
|2008-06-19 00:00:00|   15180170|
|2006-01-09 00:00:00|    1680740|
|2006-06-12 00:00:00|    1354460|
|2007-08-21 00:00:00|    3366338|
|2007-06-07 00:00:00|    2253734|
|2006-03-08 00:00:00|    1138820|
|2008-06-06 00:00:00|   11425068|
|2006-01-11 00:00:00|    1684440|
|2006-02-01 00:00:00|    1844970|
|2006-10-18 00:00:00|    1865640|
|2006-12-26 00:00:00|     880650|
|2007-07-19 00:00:00|    2898494|
|2006-09-12 00:00:00|    1098170|
|2006-12-11 00:00:00|    2685210|
|2008-09-15 00:00:00|   27020718|
|2006-12-05 00:00:00|    1577940|
+-------------------+-----------+
```

We create a new data frame with data in. From the new DF, the following queries were executed.

```sh
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
```


Show all columns from DataFrame
```sh
df.select("*").show()
val columnsAll=df.columns.map(m=>col(m))
df.select(columnsAll:_*).show()
df.select(columns.map(m=>col(m)):_*).show()

val listCols= List("lastname","country")
df.select(listCols.map(m=>col(m)):_*).show()
```

Select first 2 columns.
```sh
df.select(df.columns.slice(0,2).map(m=>col(m)):_*).show()
```

 Show columns by regular expression
 ```sh
  df.select(df.colRegex("`^.*name*`")).show()


  df.select(df.columns.filter(f=>f.startsWith("first")).map(m=>col(m)):_*).show(3)
  df.select(df.columns.filter(f=>f.endsWith("name")).map(m=>col(m)):_*).show(3)
  ```


Drop the column
```sh
df.drop(df("state")).printSchema()
```

Rename column
```sh
df.withColumnRenamed("state","estado").printSchema().show()

```

## Evaluation Practice

**Instrucciones**
**Responder las siguientes preguntas con Spark DataFrames y Scala utilizando el “CSV” Netflix_2011_2016.csv que se encuentra en la carpeta de spark-dataframes.**

1. Comienza una simple sesión Spark. 
```sh
cd Evaluationpractice
spark-shell
//:load evaluation1.scala
```
**Output**
```sh
Spark-shell
```

2. Cargue el archivo Netflix Stock CSV en dataframe llamado df, haga que Spark  infiera los tipos de datos. 

```sh
import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")
```
**Output**
```sh
+-------------------+----------+------------------+----------+----------+---------+------------------+
|               Date|      Open|              High|       Low|     Close|   Volume|         Adj Close|
+-------------------+----------+------------------+----------+----------+---------+------------------+
|2011-10-24 00:00:00|119.100002|120.28000300000001|115.100004|118.839996|120460200|         16.977142|
|2011-10-25 00:00:00| 74.899999|         79.390001| 74.249997| 77.370002|315541800|11.052857000000001|
+-------------------+----------+------------------+----------+----------+---------+------------------+
```

3. ¿Cuáles son los nombres de las columnas? 

```sh
df.columns
```
**Output**
```sh
Array[String] = Array(Date, Open, High, Low, Close, Volume, Adj Close)
```

4. ¿Cómo es el esquema?

```sh
df.printSchema()
```
**Output**
```sh
root
 |-- Date: timestamp (nullable = true)
 |-- Open: double (nullable = true)
 |-- High: double (nullable = true)
 |-- Low: double (nullable = true)
 |-- Close: double (nullable = true)
 |-- Volume: integer (nullable = true)
 |-- Adj Close: double (nullable = true)
```

5. Imprime los primeros 5 renglones. 

```sh
df.show(5)
```
**Output**
```sh
+-------------------+----------+------------------+----------+-----------------+---------+------------------+
|               Date|      Open|              High|       Low|            Close|   Volume|         Adj Close|
+-------------------+----------+------------------+----------+-----------------+---------+------------------+
|2011-10-24 00:00:00|119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|
|2011-10-25 00:00:00| 74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|
|2011-10-26 00:00:00|     78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|
|2011-10-27 00:00:00| 82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|
|2011-10-28 00:00:00| 80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|
+-------------------+----------+------------------+----------+-----------------+---------+------------------+
```

6. Usa el método describe () para aprender sobre el DataFrame. 

```sh
df.describe().show()
```
**Output**
```sh

+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|summary|              Open|              High|               Low|             Close|              Volume|         Adj Close|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
|  count|              1259|              1259|              1259|              1259|                1259|              1259|
|   mean|230.39351086656092|233.97320872915006|226.80127876251044|  230.522453845909|2.5634836060365368E7|55.610540036536875|
| stddev|164.37456353264244| 165.9705082667129| 162.6506358235739|164.40918905512854| 2.306312683388607E7|35.186669331525486|
|    min|         53.990001|         55.480001|             52.81|              53.8|             3531300|          7.685714|
|    max|        708.900017|        716.159996|        697.569984|        707.610001|           315541800|        130.929993|
+-------+------------------+------------------+------------------+------------------+--------------------+------------------+
```

7.  Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la  relación que existe entre el precio de la columna “High” frente a la columna  “Volumen” de acciones negociadas por un día. Hint - es una operación 

```sh
val df2 = df.withColumn("HV Ratio",df("High")/df("Volume"))
df2.show(5)
```
**Output**
```sh
df2: org.apache.spark.sql.DataFrame = [Date: timestamp, Open: double ... 6 more fields]
res23: Array[String] = Array(Date, Open, High, Low, Close, Volume, Adj Close, HV Ratio)

+-------------------+----------+------------------+----------+-----------------+---------+------------------+--------------------+
|               Date|      Open|              High|       Low|            Close|   Volume|         Adj Close|            HV Ratio|
+-------------------+----------+------------------+----------+-----------------+---------+------------------+--------------------+
|2011-10-24 00:00:00|119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|9.985040951285156E-7|
|2011-10-25 00:00:00| 74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|2.515989989281927E-7|
|2011-10-26 00:00:00|     78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|5.474206014903126E-7|
|2011-10-27 00:00:00| 82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|1.161960907430818...|
|2011-10-28 00:00:00| 80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|1.465476686700271...|
+-------------------+----------+------------------+----------+-----------------+---------+------------------+--------------------+
```

8. ¿Qué día tuvo el pico más alto en la columna “Open”?

```sh
df.orderBy($"Open".desc).show(1)
```
**Output**
```sh
+-------------------+----------+----------+----------+----------+--------+----------+
|               Date|      Open|      High|       Low|     Close|  Volume| Adj Close|
+-------------------+----------+----------+----------+----------+--------+----------+
|2015-07-14 00:00:00|708.900017|711.449982|697.569984|702.600006|19736500|100.371429|
+-------------------+----------+----------+----------+----------+--------+----------+
```

9. ¿Cuál es el significado de la columna Cerrar “Close” en el contexto de información  financiera, explíquelo no hay que codificar nada? 

```sh
La columna close indica el valor en el que Cierra ese dia
```
**Output**
```sh
+-------------------+----------+------------------+----------+-----------------+---------+------------------+
|               Date|      Open|              High|       Low|            Close|   Volume|         Adj Close|
+-------------------+----------+------------------+----------+-----------------+---------+------------------+
|2011-10-24 00:00:00|119.100002|120.28000300000001|115.100004|       118.839996|120460200|         16.977142|
|2011-10-25 00:00:00| 74.899999|         79.390001| 74.249997|        77.370002|315541800|11.052857000000001|
|2011-10-26 00:00:00|     78.73|         81.420001| 75.399997|        79.400002|148733900|         11.342857|
|2011-10-27 00:00:00| 82.179998| 82.71999699999999| 79.249998|80.86000200000001| 71190000|11.551428999999999|
|2011-10-28 00:00:00| 80.280002|         84.660002| 79.599999|84.14000300000001| 57769600|             12.02|
+-------------------+----------+------------------+----------+-----------------+---------+------------------+
```
10. ¿Cuál es el máximo y mínimo de la columna “Volumen”? 

```sh
df.select(min("Volume")).show()
df.select(max("Volume")).show()
```
**Output**
```sh
+-----------+
|min(Volume)|
+-----------+
|    3531300|
+-----------+


+-----------+
|max(Volume)|
+-----------+
|  315541800|
+-----------+
```

11. Con Sintaxis Scala/Spark $ conteste lo siguiente: 

a). ¿Cuántos días fue la columna “Close” inferior a $ 600? 

```sh
df.filter($"Close"<600).count()

```
**Output**
```sh
res25: Long = 1218
```

b). ¿Qué porcentaje del tiempo fue la columna “High” mayor que $ 500? 

```sh
(df.filter($"High">500).count()*100.00)/df.count()
```
**Output**
```sh
Double = 4.924543288324067
```

c). ¿Cuál es la correlación de Pearson entre columna “High” y la columna  “Volumen”? 

```sh
 df.select(corr("High","Volume")).show()
```
**Output**
```sh
+--------------------+
|  corr(High, Volume)|
+--------------------+
|-0.20960233287942157|
+--------------------+
```

d). ¿Cuál es el máximo de la columna “High” por año?

```sh
val df3 = df.withColumn("Year", year(df("Date")))
val dfprom = df3.groupBy("Year").max()
dfprom.select($"Year", $"max(High)").show()
```
**Output**
```sh
+----+------------------+
|Year|         max(High)|
+----+------------------+
|2015|        716.159996|
|2013|        389.159988|
|2014|        489.290024|
|2012|        133.429996|
|2016|129.28999299999998|
|2011|120.28000300000001|
+----+------------------+
```

e). ¿Cuál es el promedio de la columna “Close” para cada mes del calendario?

```sh
val df4 = df.withColumn("Year", year(df("Date"))).withColumn("Month", month(df("Date")))
val df5 = df4.select(concat($"Year", lit("-"), $"Month").as("M/Y"),$"Close")
val dfavgs = df5.groupBy("M/Y").mean().sort("M/Y").show(72)
```
**Output**
```sh
+----+---------------------+
|M/Y    |         Close    |
+-------+------------------+
|2011-10| 87.11500133333334|
|2011-11| 79.76380923809522|
|2011-12| 70.30428566666667|
| 2012-1| 97.75149895000001|
|2012-10| 65.78095142857143|
|2012-11| 80.04190514285713|
|2012-12| 89.40500014999998|
| 2012-2|119.92049895000002|
| 2012-3|113.00181809090908|
| 2012-4|100.88399985000001|
| 2012-5| 72.98772681818181|
| 2012-6| 65.75380899999999|
| 2012-7|  75.2542851904762|
| 2012-8|60.736521347826084|
| 2012-9| 56.57736921052631|
| 2013-1|117.01380985714289|
|2013-10| 321.7186945217391|
|2013-11|      342.68350005|
|2013-12|368.05095304761903|
| 2013-2|182.45684194736842|
| 2013-3|       184.6934989|
| 2013-4|182.83045363636364|
| 2013-5|223.71999777272728|
| 2013-6|218.92799680000002|
| 2013-7|246.11908731818184|
| 2013-8|264.21408818181817|
| 2013-9|      303.47000175|
| 2014-1| 359.1285730952381|
|2014-10| 412.4830449565217|
|2014-11| 373.4242098421052|
|2014-12|339.59772645454535|
| 2014-2| 431.4189474210526|
| 2014-3| 414.8538118571429|
| 2014-4|340.62333161904763|
| 2014-5| 362.4299989523809|
| 2014-6|433.30476138095236|
| 2014-7|       442.4186355|
| 2014-8| 457.2204742380952|
| 2014-9| 462.8152389523809|
| 2015-1|373.86549944999996|
|2015-10|105.58772759090908|
|2015-11|116.06949959999997|
|2015-12|121.29636322727274|
| 2015-2| 462.9221038421052|
| 2015-3|437.66090550000007|
| 2015-4| 505.8238118095239|
| 2015-5| 596.3994980499999|
| 2015-6| 652.4349957727273|
| 2015-7|339.94727122727267|
| 2015-8|117.50285638095235|
| 2015-9|100.56571519047618|
| 2016-1|105.44789489473683|
|2016-10|    109.0562500625|
| 2016-2| 89.96950000000001|
| 2016-3| 99.31590927272727|
| 2016-4|100.81190457142858|
| 2016-5| 93.17476147619047|
| 2016-6| 94.29863663636364|
| 2016-7| 93.16349985000002|
| 2016-8| 95.62260904347829|
| 2016-9| 97.44857128571428|
+-------+------------------+

```
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

