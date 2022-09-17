 //def isEven(num:Int): Boolean = {
    // return num%2 == 0
// }
//def isEven(num:Int): num%2 == 0
  //  println(isEven(6))
  //  println(isEven(3))
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
//Explication: The "afortunado" function receives an integer type list and returns an integer data type
//the variable res = 0 is initialized, a cycle for will run the list, in the for a if it tells us that when in the list there is a number equal to 7 to the variable res(accumulator) 14 will be added, and otherwise with a else a res(accumulator) the number will be added within the list,  finally in the return we return the final result of res (accumulator).
//Finally we print the result of the "afortunado" function with the list "af", which gives us 29, the result of the sum 14 + 14 + 1 within the for.

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
//29


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
