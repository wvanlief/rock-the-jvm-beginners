package lectures.Part2oot

object Objects extends App{

   // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONNALITY
   // YOU ACCESS INSTANCIATED OBJECTS
  object Person { // object are like class without parameter, you can access stuff the same way
     val N_EYES = 2
     def canFly: Boolean = false
     // This is a singleton instance, there is only ONE that can be access with person !

     // Factory method, because it builds persons
     def apply(mother: Person, father: Person): Person = new Person("Boby")
   }

  class Person(val name: String) {
    // instance level functionnality
  }
    // class and object person are COMPANIONS
  // All code is accessed through instances ! the class or object


  println(Person.N_EYES)

  // SCALA Object is a singleton instance !
  // Object is it's own type (person here) and it's only instance !

  val mary = Person
  val john = Person
  println(mary == john) //this is true ITS THE SAME INSTANCE

  val person1 = new Person(name = "Mary")
  val person2 = new Person(name = "John")
  println(person1==person2) // this is false because we instanciated two different classes !

  val boby = Person(person1,person2) // like a constructor but from the object

  // Scala applications = Scala Objects with
  // def main(args: Array([String]): Unit // can be used instead of extends app !
}
