package support

data class Settlement(val from: Person, val to: Person, val amount: Double)

class Person(val name: String) {

    companion object {
        val people: MutableList<Person> = mutableListOf()
        val transactions: MutableMap<Int, Transaction> = mutableMapOf()

        fun getBalances(): Map<Person, Double> {
            val balances = mutableMapOf<Person, Double>()
            transactions.values.forEach { tx ->
                balances[tx.payer] = (balances[tx.payer] ?: 0.0) - tx.amount
                val part = tx.amount / tx.splitBetween.size
                tx.splitBetween.forEach { balances[it] = (balances[it] ?: 0.0) + part }
            }
            return balances
        }

        fun splitTheBill(): List<Settlement> {
            val settlements = mutableListOf<Settlement>()
            val balances = getBalances().map { Pair(it.key, it.value) }

            val payers = balances.filter { it.second > 0 }.toSortedSet(Comparator {o1, o2 -> o2.second.compareTo(o1.second)})
            val payees = balances.filter { it.second < 0 }.toSortedSet(Comparator {o2, o1 -> o1.second.compareTo(o2.second)})

            while (payers.isNotEmpty() && payees.isNotEmpty()) {
                val payer = payers.first()
                payers.remove(payer)

                val payee = payees.first()
                payees.remove(payee)

                val amount = Math.min(payer.second, -payee.second)
                settlements.add(Settlement(payer.first, payee.first, amount))

                if (payer.second > amount) {
                    payers.add(Pair(payer.first, payer.second - amount))
                }

                if (-payee.second > amount) {
                    payees.add(Pair(payee.first, payee.second + amount))
                }

            }

            return settlements
        }

    }

    init {
        people.add(this)
    }

    infix fun pays(amount: Double) {
        val tx = Transaction(this, amount, null, null, people)
        transactions[tx.id] = tx
    }

    infix fun split(amount: Double): TransactionBuilder = TransactionBuilder(this, amount)

    infix fun gives(amount: Double) = split(amount)

    override fun toString(): String = name

}