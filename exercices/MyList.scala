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

}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String =
    if(t.isEmpty) "" + h
    else h + " " + t.printElements

  def map[B](transformer: MyTransformer[A,B]): MyList[B]

  def flatmap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]

  def filter(predicate: MyPredicate[A]): MyList[A] =
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

}

trait MyPredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A,B] {
  def transform(elem: A): B
}

object ListTest extends App {
  val listOfInt: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val listOfString: MyList[String] = new Cons("Hello", new Cons("World", Empty))

  println(listOfInt.toString)
  println(listOfString.toString)

  // polymorphic call, apply the function based on the class it was defined in
  //println(list.toString)
}