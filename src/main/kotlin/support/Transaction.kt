package support

data class Transaction(
        val id: Int,
        val payer: Person,
        val amount: Double,
        private val location: String?,
        private val time: String?,
        val splitBetween: List<Person>) {

    constructor(
            payer: Person,
            amount: Double,
            location: String?,
            time: String?,
            splitBetween: List<Person>) : this(nextId(), payer, amount, location, time, splitBetween)

    companion object {
        private var txId = 0
        fun nextId() = txId++
    }

}