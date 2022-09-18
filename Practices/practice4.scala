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