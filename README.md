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
