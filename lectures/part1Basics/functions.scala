package lectures.part1Basics

object functions extends App{

  def aFunction(a: String, b: Int): String = {// def functionNAme(param1: param1Type, ...) : returnType
    a + " " + b
  }

  def aLoop(aString: String, n: Int): String ={
    if (n==1) aString
    else aString + aLoop(aString, n-1)
  }
  // WHEN YOU NEED LOOPS , USE TAIL END RECURSION

  println(aLoop("hello",3))

  def greetings(name: String, age: Int): String = "Hi my name is " +name+" and I am "+age+" years old."
  println(greetings("William",25))

  def factorial(n: Int): Int = // dont need curly brackets, only one instruction
    if (n==0) 1
    else factorial(n-1)*n

  println(factorial(4))

  def fibonacci(n: Int): Int =
    if (n==1|n==2) 1
    else fibonacci(n-1) + fibonacci(n-2)

  println(fibonacci(8))

  def prime(n: Int): Boolean = {
    def isPrimeUntil(t: Int): Boolean =
      if (t<=1) true
      else n % t !=0 && isPrimeUntil(t-1)

    isPrimeUntil(n / 2)
  }
  println(prime(17))
  println(prime(25))
  println(prime(2003))
}
