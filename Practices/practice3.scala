 def isEven(num:Int): Boolean = {
     return num%2 == 0
 }
def isEven(num:Int): num%2 == 0
    println(isEven(6))
    println(isEven(3))
//Practice 3, analyse the following code with your own words

def isEven(num:Int): Boolean = {
     return num%2 == 0
 }
def isEven(num:Int): num%2 == 0
    println(isEven(6))
    println(isEven(3))
//Practice 3, analyse the following code with your own words

// This function takes a list, validates and prints if the numbers within the list are ODD or EVEN, 
//at the very end it will print DONE

def listEvens(list:List[Int]): String ={ // define una func listEvents tipo INT que va a regresar un STRING
    for(n <- list){                     // ciclo FOR para recorrer los valores en la lista 
        if(n%2==0){                     // if para determinar que un numero sera odd o even con MOD %
            println(s"$n is even")      // si el sobrante de MOD es 0 se imprime el numero y texto que indica que es EVEN
        }else{                          // si no es asi    
            println(s"$n is odd")       //si el sobrante de MOD NO es 0 se imprime el numero y texto que indica que es ODD
        }
    }
    return "Done"                       // imprime done al terminar de recorrer la lista
}

val l = List(1,2,3,4,5,6,7,8)           //Se crea una lista (1) con los valores predeterminados
val l2 = List(4,3,22,55,7,8)            //Se crea una lista con los valores predeterminados
listEvens(l)                            //Se llama la funcion ListEvens previamente creada con la list 1
listEvens(l2)                           ////Se llama la funcion ListEvens previamente creada con la list 12

//3 7 afortunado

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
//false
