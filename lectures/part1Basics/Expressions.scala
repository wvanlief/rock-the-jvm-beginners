package lectures.part1Basics

object Expressions extends App{
        val x = 1+2 // This is an Expression
  // Expression are mathematical or logical operations
        println(x)

  // Instructions(computer do THIS) vs Expressions (has a VALUE/Type)

  // IF expression
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3 // IF EXPRESSION returns a VALUE
  println(aConditionValue)

    // EVERYTHING in Scala is an Expression !

  // Side effects (re assign) are expression that returns units, type void

  // code blocks
  val aCodeBlock  = {
    val y = 2
    val z = y +1

    if (z == 2) "Hello" else "goodbye"
   }


}
