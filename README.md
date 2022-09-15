# Clase Big Data
Gabriela Aguilar, Humberto Chavez y Ricardo Chavez

## Practice 1
```sh
//Desarrollar un algoritmo en scala que calcule el radio de un circulo

val Diameter: Int = 20
val Radio: Int = Diameter / 2

//Desarrollar un algoritmo en scala que me diga si un numero es primo
var num = 7
if (num % 2 == 0) {
    printf("No es número primo")
} else {
    printf("Si es número primo")
}

// Dada la variable bird = "tweet", utiliza interpolacion de string para
    imprimir "Estoy ecribiendo un tweet"

var Bird: String = "tweet"
var PrintTweet = s"Estoy escribiendo un ${Bird}"

//Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la
//   secuencia "Luke"

val msn = "Hola Luke yo soy tu padre!"
msn slice (5,9)

//¿Cual es la diferencia entre value y una variable en scala?
//Los valores de "val" son inmutables a diferencia de "var" que los valores van cambiando.

//Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el numero 3.1416
val tup = (2,4,5,1,2,3,3.1416,23) 
tup._7
```
## Practice 2
```sh
//Practice 2
// 1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"
val lista = List("rojo","blanco","negro")

// 2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"
val lista = List("rojo","blanco","negro","verde","amarillo","azul","naranja","perla")

// 3. Traer los elementos de "lista" "verde", "amarillo", "azul"
lista slice (3,6)

// 4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5
Array.range(0, 1000, 5)

// 5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos
val s = Set(1,3,3,4,6,7,3,7)

// 6. Crea una mapa mutable llamado nombres que contenga los siguiente
//     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"
val mutmap = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", 27))

// 6 a . Imprime todas la llaves del mapa
mutmap.keys

// 7 b . Agrega el siguiente valor al mapa("Miguel", 23)
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