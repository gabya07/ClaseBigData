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
## Practice 2
```sh




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

```