package lectures.Part2oot

object AbstractDataTypes extends App{
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
    // do not supply values for val and func >>> abstract class
    // cannot be instanciated
    // needs to be implemented in the subclasses
    // can have abstract and non abstract members
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch") ///override not necessary

  }

  //traits
  trait ColdBlodded
  trait Carnivore { // traits can have abstracts and non abstract members
    def eat(animal: Animal):  Unit
    val preferredMeal: String = "meat "
    // traits do not have constructor parameters
    // can inherit ONE class but MULTIPLE traits
    // traits are behavior , abstract class are "thing"
    // class are animal, traits are what they do
  }

  class Crocodile extends Animal with Carnivore with ColdBlodded { // extends a CLASS with a TRAIT (or many)
    override val creatureType: String = "croc"
    override def eat: Unit = println("nomnomnom")
    override def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")

  }
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
}
