package support

class TransactionBuilder(private val payer: Person, private val amount: Double) {
    private val id = Transaction.nextId()
    private val splitBetween = mutableListOf<Person>()
    private var location: String? = null
    private var time: String? = null

    infix fun between(person: Person): TransactionBuilder {
        splitBetween.add(person)
        val tx = Transaction(id, payer, amount, location, time, listOf(*splitBetween.toTypedArray()))
        Person.transactions[id] = tx
        return this
    }

    infix fun and(person: Person): TransactionBuilder = between(person)

    infix fun to(person: Person): TransactionBuilder = between(person)

    infix fun on(time: String): TransactionBuilder {
        this.time = time
        val tx = Transaction(id, payer, amount, this.location, time, listOf(*splitBetween.toTypedArray()))
        Person.transactions[id] = tx
        return this
    }

    infix fun at(location: String): TransactionBuilder {
        this.location = location
        val tx = Transaction(id, payer, amount, location, this.time, listOf(*splitBetween.toTypedArray()))
        Person.transactions[id] = tx
        return this
    }

}