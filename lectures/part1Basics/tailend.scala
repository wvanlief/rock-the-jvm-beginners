package lectures.part1Basics

object tailend extends App{
  def anotherFact(n: Int): BigInt = {
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if(x<=1) accumulator
      else factHelper(x-1, accumulator*x)

    factHelper(n,1)
  }
  // this is tail end recursion because the recursion is the last call of the code block
  // need to create accumulator in the helper function because you need to call this function in itself, reattribution trick
  // this works on big numbers because the stack of recursion is not kept in memory thanks to the accumulator
  // the intermediate computation are saved in the accumulator instead of the memory basically

  println("hello"*5)

  def tailConcat(s: String, n: Int): String = {
    s*n
  }

//  def tailPrime(n: Int): Boolean = {}
  // 1 1 2 3 5 8 13 21 34 56 90
  // fibonacci(n-1) + fibonacci(n-2)

  def tailFibo(n: Int): BigInt = {
    def tailFiboHelp(i: Int, last: BigInt, previousLast: BigInt): BigInt = //Need 2 accumulator for fibo-1 and fibo-2
      if(i>=n) last // we go up to the inputted number
      else tailFiboHelp(i+1, last+previousLast, last) // last + previous last is the fibonacci formula in practice

    if(n<=2) 1
    else tailFiboHelp(2, 1, 1) //we force start at the third instance, to have 1 iteration
  }

  println(tailFibo(8))
}
