package lectures.Part2oot

object inheritance extends App {

  // single class inheritance
  sealed class Animal { //final prevents class to be overriden ! you can use final on member/function/entire class
    def eat = println("nomnom")
    val creatureType = "wild"
  }

  class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.crunch


  // constructor
  class Person (name: String, age: Int) {
    def this(name: String) = this(name,0)
  }

  class Adult(name: String, age: Int, idCard: String ) extends Person(name) //when you extends you need to call the constructor that exists, with its parameters

  class Dog(override val creatureType : String ) extends Animal { // override while construction the object
    override def eat = {
      super.eat // call the func from the super class
      println("crunch crunch") // rewrite the class function, works for val and func
      // override val creatureType = "domestic"
    }
  }

  // type substitution : polymorphism
  val unknownAnimal : Animal = new Dog("K9")
  unknownAnimal.eat // use the most recent overridden function even if it's an Animal, because we constructed with dog

  // preventing overrides
  // 1 - use final on member (function, val) in the super class
  // 2 - use final before the class, prevents all override
  // 3 - use sealed to prevent extention in other files but fine in this file
}
