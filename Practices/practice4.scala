

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