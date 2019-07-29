package exercices

abstract class MyList[+A] { //list of integer
  // own collection
  /*
  head = first element of the list
  tail = remainder of the list
  isEmpty = is the list empty
  add(int) = new list with this element added
  toString = a string representation of the list
 */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" +printElements + "]"

  def map[B](transformer: MyTransformer[A,B]): MyList[B]
  def flatmap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]

  //concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]
}

object Empty extends MyList[Nothing] { // like the exception, empty should be a correct return type for any list
  // def head: Int = throw new NoSuchElementException
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing,B]): MyList[B] = Empty
  def flatmap[B](transformer: MyTransformer[Nothing,MyList[B]]): MyList[B] = Empty
  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements



  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate) // what does test do ??? >> here nothing, you need to override and re-implement it

  def map[B](transformer: MyTransformer[A,B]): MyList[B] =
    new Cons(transformer.transform(h), t.map(transformer))

  /*  WHAT THIS MAP DOES :
    [1,2,3].map(n * 2) :: n * 2 is the transformer
      = new Cons (transform(1) , [2,3].map(n * 2))
      = new Cons (2, new Cons(transform(2), [3].map(n * 2)))
      = new Cons (2, new Cons(4, new Cons(transform(3), Empty.map(n * 2))))
      = new Cons (2, new Cons(4, new Cons(6, Empty))))
   */

  def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, t ++ list)
  /*
  [1,2] ++ [3,4,5]
  = new Cons(1, [2] ++ [3,4,5])
  = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
  = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5)))))
   */

  def flatmap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B] =
    transformer.transform(h) ++ t.flatmap(transformer)
  /*
  [1,2].flatMap(n => [n, n+1]) >>>> elem n become list of [n, n+1], need to rewrite the transform method
  = [1,2] ++ [2].flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
  = [1,2] ++ [2,3] ++ Empty
  = [1,2,2,3]
   */

}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A,B] {
  def transform(elem: A): B
}

object ListTest extends App {
  val listOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherlistOfInt: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfString: MyList[String] = new Cons("Hello", new Cons("World", Empty))

  println(listOfInt.toString)
  println(listOfString.toString)

  // polymorphic call, apply the function based on the class it was defined in
  //println(list.toString)

  println(listOfInt.map(new MyTransformer[Int, Int] {
    // when invocking a new MyTrasnformer, the IDE automatically call the override on the correct function !
    override def transform(elem: Int): Int = elem * 2 // here we write the correct n * 2 from above comment
  }).toString)

  println(listOfInt.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 ==0
  }).toString)

  println((listOfInt ++ anotherlistOfInt).toString)

  println(listOfInt.flatmap(new MyTransformer[Int, MyList[Int]] {
    override def transform(elem: Int): MyList[Int] = new Cons(elem, new Cons(elem + 1, Empty)) // here is the correct transformer again
  }).toString)
}