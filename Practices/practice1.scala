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
var Write: String = "Estoy escribiendo un "
var PrintTweet: String = Write + Bird


//4. Dada la variable mensaje = "Hola Luke yo soy tu padre!" utiliza slilce para extraer la
//   secuencia "Luke"

val msn = "Hola Luke yo soy tu padre!"
msn slice (5,9)

//5 Los valores de "val" son inmutables a diferencia de "var" que los valores van cambiando.