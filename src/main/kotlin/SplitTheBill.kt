import support.Person

fun main(args: Array<String>) {
    val Alex = Person(name = "Alex")
    val Eugen = Person(name = "Eugen")
    val Zjor = Person(name = "Zjor")
    val Vlad = Person(name = "Vlad")
    val Oleg = Person(name = "Oleg")


    Vlad gives 31.6 to Zjor
    Eugen gives 4.0 to Zjor
    Alex gives 30.0 to Zjor
    Alex pays 30.0

    Oleg split 10.0 between Alex and Eugen on "12/04/2018" at "Cafe Mood"

    Person.transactions.forEach{ println(it) }


    Person.getBalances().forEach{ println("${it.key}: \t ${it.value}") }

    Person.splitTheBill().forEach { println(it) }


}