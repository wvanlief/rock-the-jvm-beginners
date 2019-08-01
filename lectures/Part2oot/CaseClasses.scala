package lectures.Part2oot

object CaseClasses extends App{

  case class Person(name: String, age: Int)

  //case class automatically implement reasonnable class function like equals, hashCode, toString

  val jim = new Person("Jim", 34)
  println(jim.name) // implemented sensible toString function

  // equals and hashCode implemented out of the box !
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true !

  // 4. have handy copy methods :)
  val jim3 = jim.copy(age = 45) // new instance of the CASE class, copy can receive new parameter that are passed on the new instance

  //5. have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // companion object apply method(that let us call the object like a function) automatically implemented by the case class

  //6. CCs are serializable
  // important in Akka

  //7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
