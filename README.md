# Clase Big Data
**Teamwork: Gabriela Aguilar, Humberto Chavez y Ricardo Chavez**
```diff
- text in red
+ text in green
! text in orange
# text in gray
@@ text in purple (and bold)@@
```
## Content
- [Clase Big Data](#clase-big-data)
  * [Practice 1](#practice-1)
  * [Practice 2](#practice-2)
  * [Practice 3](#practice-3)
  * [Practice 4](#practice-4)
  * [Practice 5](#practice-5)

## Practice 1
Develop an algorithm that calculates the **radius of a circle**
```sh
val Diameter: Int = 20
val Radio: Int = Diameter / 2
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
Given the variable bird = "tweet", use string interpolation to print **"I'm writing a tweet"**
```sh
var Bird: String = "tweet"
var PrintTweet = s"Estoy escribiendo un ${Bird}"
```
Given the message variable = "Hello Luke, I'm your father!" use slice to extract the sequence **"Luke"**
```sh
val msn = "Hola Luke yo soy tu padre!"
msn slice (5,9)
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
## Practice 2

Create a list called "list" with the elements **"red", "white", "black"**

```sh
val lista = List("rojo","blanco","negro")
```
Add 5 more items to **"list" "green" ,"yellow", "blue", "orange", "pearl"**

```sh
val lista = List("rojo","blanco","negro","verde","amarillo","azul","naranja","perla")
```
Fetch items from "list" **"green", "yellow", "blue"**
```sh
lista slice (3,6)
```
Create an array of numbers in the range **1-1000** in steps of 5 at a time
```sh
Array.range(0, 1000, 5)
```
What are the unique elements of the list **List(1,3,3,4,6,7,3,7)** use conversion to sets
```sh
val s = Set(1,3,3,4,6,7,3,7)
```
Create a mutable map named names containing the following **"Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"**
#### **Print all** the keys on the map
#### Add the following value to the map **("Miguel", 23)**

```sh
val mutmap = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))
mutmap.keys
mutmap += ("Miguel" -> 23)
```
## Practice 3
```sh
//Exercise #1

// This function takes a list, validates and prints if the numbers within the list are ODD or EVEN, 
//at the very end it will print DONE

def listEvens(list:List[Int]): String ={ // define a func listEvents type INT, it will return a STRING value
    for(n <- list){                     // FOR loop to go through the list values 
        if(n%2==0){                     // if condition to make sure a number divided by two will return 0 MOD%
            println(s"$n is even")      // if number %2 is 0 it will print the number and the word  EVEN
        }else{                          // si no es asi    
            println(s"$n is odd")       //if number %2 is NOT 0 it will print the number and the word  ODD
        }
    }
    return "Done"                       // At the end of the list it will print the WORD DONE
}

val l = List(1,2,3,4,5,6,7,8)           //Creating a list (1) with values
val l2 = List(4,3,22,55,7,8)            //Creating a list (12) with values
listEvens(l)                            //call the func ListEvens that was created with param list(1)
listEvens(l2)                           ////call the func ListEvens that was created with param list(12) 

# Run output
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


// Exercise #2 
//
Explication: The "afortunado" function receives an integer type list and returns an integer data type
the variable res = 0 is initialized, a cycle for will run the list, in the for a if it tells us that when in the list there is a number equal to 7 to the variable res(accumulator) 14 will be added, and otherwise with a else a res(accumulator) the number will be added within the list,  finally in the return we return the final result of res (accumulator).
Finally we print the result of the "afortunado" function with the list "af", which gives us 29, the result of the sum 14 + 14 + 1 within the for.

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
// 29

Exercise #4
//This Function get a list and compare by itself;
//First gets SEGUNDA as the sum of the same list (B1=6) and Primera as (0) on the FOR, 
//PRIMERA is adding the list item and SEGUNDA is subtracting the list item, 
//in each cases the final result is compared between and if is the same prints TRUE
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
//res5: Boolean = true
//res6: Boolean = true
//res7: Boolean = false

Exercise #5
//This function validates the word with the same word in a reverse way
//And if is the same with the reverse it prints True
def palindromo(palabra:String):Boolean ={
    return (palabra == palabra.reverse)
}
val palabra = "OSO"
val palabra2 = "ANNA"
val palabra3 = "JUAN"

println(palindromo(palabra))
println(palindromo(palabra2))
println(palindromo(palabra3))
//true
//true
//false............./////
```
## Practice 4
```sh
// Exercise 1
def fibonacci_algoritmo_1(n: Int): Int = {
    if      (n < 2) n
    else    fibonacci_algoritmo_1(n-1) + fibonacci_algoritmo_1(n-2)
  }
fibonacci_algoritmo_1(10)
//55

//Exercise 3
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
//89

//Exercise 4
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
//1597

//Exercise 5
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
//List[Int] = List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765)
```

## Practice 5
```sh

// 1 Adding a Column CarData CSV
//withcolumn adds a new column to existing dataframe with its value 
// val df = spark.read.option("header", "true").option("inferSchema","true")csv("CarData")

df.withColumn("Seats", lit(7)).show(1)
// +---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
|Car_Model| mpg|cyl| disp| hp|drat|  wt| qsec| vs| am|gear|carb|Seats|
+---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+
|Mazda RX4|21.0|  6|160.0|110| 3.9|2.62|16.46|  0|  1|   4|   4|    7|
+---------+----+---+-----+---+----+----+-----+---+---+----+----+-----+

// 2 Using select and summary to pull  data from specific columns un the dataframe 

df.select("Car_model", "Cyl").summary("min","max", "count").show()
+-------+-----------+---+
|summary|  Car_model|Cyl|
+-------+-----------+---+
|    min|AMC Javelin|  4|
|    max| Volvo 142E|  8|
|  count|         32| 32|
+-------+-----------+---+


//3 Drop   using drop to delete single  and multiple columns from the DATAFRAME
df.drop("Car_Model", "wt", "mpg").show(1)
+----+---+-----+---+----+----+-----+---+---+----+----+
| mpg|cyl| disp| hp|drat|  wt| qsec| vs| am|gear|carb|
+----+---+-----+---+----+----+-----+---+---+----+----+
|21.0|  6|160.0|110| 3.9|2.62|16.46|  0|  1|   4|   4|
+----+---+-----+---+----+----+-----+---+---+----+----+

df.drop("Car_Model", "wt", "mpg").show(1)
+---+-----+---+----+-----+---+---+----+----+
|cyl| disp| hp|drat| qsec| vs| am|gear|carb|
+---+-----+---+----+-----+---+---+----+----+
|  6|160.0|110| 3.9|16.46|  0|  1|   4|   4|
+---+-----+---+----+-----+---+---+----+----+

//4 Using Fill and FILL NA to fill null / empty valus in a dataframe
+---+-------+--------+-------------------+-----+----------+
| id|zipcode|    type|               city|state|population|
+---+-------+--------+-------------------+-----+----------+
|  1|    704|STANDARD|               null|   PR|     30100|
|  2|    704|    null|PASEO COSTA DEL SUR|   PR|      null|
|  3|    709|    null|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|  UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|STANDARD|               null|   TX|      null|
+---+-------+--------+-------------------+-----+----------+

df.na.fill(value=0).show()  #this will fill any null INT  with a any INT value we enter, in this case 0.
+---+-------+--------+-------------------+-----+----------+
| id|zipcode|    type|               city|state|population|
+---+-------+--------+-------------------+-----+----------+
|  1|    704|STANDARD|               null|   PR|     30100|
|  2|    704|    null|PASEO COSTA DEL SUR|   PR|         0|
|  3|    709|    null|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|  UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|STANDARD|               null|   TX|         0|
+---+-------+--------+-------------------+-----+----------+
df.na.fill(value="PRACTICA-BIGDATA").show() #This will fill any null string with the given value#

+---+-------+----------------+-------------------+-----+----------+
| id|zipcode|            type|               city|state|population|
+---+-------+----------------+-------------------+-----+----------+
|  1|    704|        STANDARD|   PRACTICA-BIGDATA|   PR|     30100|
|  2|    704|PRACTICA-BIGDATA|PASEO COSTA DEL SUR|   PR|      null|
|  3|    709|PRACTICA-BIGDATA|       BDA SAN LUIS|   PR|      3700|
|  4|  76166|          UNIQUE|  CINGULAR WIRELESS|   TX|     84000|
|  5|  76177|        STANDARD|   PRACTICA-BIGDATA|   TX|      null|
+---+-------+----------------+-------------------+-----+----------+.


// 6.-From the "CitiGroup2006_2008" table, we calculate the maximum number of High and the minimum number of Low grouped by Volume.

df.groupBy($"Volume").agg(Map("High" -> "max","Low" -> "min")).show()
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

//7.- From the "CitiGroup2006_2008" table, we calculate average of Open and the average of Close grouped by Date.

df.groupBy($"Date").agg(Map("Open" -> "avg","Close" -> "avg")).show()
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


//8.- Compute the average for all numeric columns rolled up by Date.

df.rollup("Date").avg().show()
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

// 9. We order the table "CitiGroup2006_2008" sorted by Open descending and Close ascending

df.sort($"Open".desc, $"Close".asc).show()
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

//10.-From the "CitiGroup2006_2008" table, we calculate the maximum number of Volume grouped by Date 

df.groupBy(df.col("Date")).agg(max("Volume")).show()
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
