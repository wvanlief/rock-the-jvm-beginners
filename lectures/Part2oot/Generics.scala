package lectures.Part2oot

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
    A = Cat
    B = Dog = Animal
    IF i add a dog (B) to a list of cat(A) then list return a list of animal (B) B is super type of A
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???  //type of the method is generic here it takes the type you gave it "A" and return a list of that type
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem !
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  //1. yes List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // if i have a list of animals that is in fact a list of cats, what if i add a dog to it ? -> turn it into a list of Animal (more generic)
  // animalList.add(new Dog) ??? HARD QUESTION
  // can you add dogs to a list of cats? it's polluting the initial list and making subclass distinction less useful

  //2. NO = INVARIANCE
  class invariantAnimalList[A]
  val invariantAnimalList: invariantAnimalList[Animal] = new invariantAnimalList[Animal]
  // here you have to use animal each time, the type is invariant

  //3. Hell, no ! CONTRAVARIANCE
  class ContravariantList[-A] // with a minus
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]
  // trainer of animal is better, it trains all animal, amongst them cat in particular
  // using a trainer of Animal (general) to train a cat (specific)
  // opposite to covariance

  //bounded types
  class Cage[A <: Animal](animal: A) // class cage only accept SUBTYPES of Animal
  val cage = new Cage(new Dog)

  //expand myList to be generic



}

