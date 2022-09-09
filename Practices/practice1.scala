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