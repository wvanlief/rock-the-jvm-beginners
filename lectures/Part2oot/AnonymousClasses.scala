package lectures.Part2oot

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit

  }

  val funnyAnimal: Animal = new Animal { //anonymous class
    override def eat: Unit = println("ahahahahahah")
  } // we can instantiate types and override fields or methods on the spot
    // here we instantiate a new animal and override the abstract eat function
  /*
  equivalent with

  class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("ahahahahhah")
      }
      val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = print(s"Hi, my name is $name, how can i help ?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can i be of service?")
  }
}
