package lectures.Part2oot

object OOBasics extends App{
  val person = new Person("John", 26)
  person.greet("Daniel")
  person.greet()
}

//the constructor
class Person(name: String,val age:Int) {

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name") // class method
  def greet(): Unit = println(s"Hi, I am $name") // overloading the method by changing number of parameter

  //multiple constructors
  def this(name: String) = this(name, 0) // aux constructor with already initialized age
}

// class parameters are not FIELDS
// convert parameters into fields using val or var !

class Writer(first_name: String, last_name: String, val year: Int) { // val to make a field to make it accessible
  def fullname(): String = first_name+" "+last_name
}

class Novel(name: String,release: Int, author: Writer) {
  def authorAge(): Int = release - author.year
  def isWritten(anAuthor: Writer): Boolean = author==anAuthor
  // no need for different name can do isWritten(author): Boolean = author==THIS.author , THIS call the novel author
  def copy(newRelease: Int): Novel = new Novel(name, newRelease, author)
}

class Counter(val count: Int){ // when adding val we dont need a return function (getter)
  def increment(): Counter = new Counter(count + 1)
  def decrement(): Counter = new Counter(count -1)
  def increment(inc: Int): Counter = {
    if(inc<=0) this
    else increment.increment(count - 1 )
  }
  def decrement(dec: Int): Counter = new Counter(count - dec)

}