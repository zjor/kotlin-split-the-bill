package support

import org.junit.Assert
import org.junit.Test

class PersonTest {

    @Test
    fun `should calculate balances when giving`() {
        val Alice = Person("Alice")
        val Bob = Person("Bob")

        Alice gives 10.0 to Bob

        Assert.assertEquals(mapOf(Alice to -10.0, Bob to 10.0), Person.getBalances())
    }

    @Test
    fun `should split the bill`() {
        val a = Person("a")
        val b = Person("b")
        val c = Person("c")

        a gives 1.0 to b
        b gives 1.0 to c
        c gives 2.0 to a

//        Person.splitTheBill().forEach { println(it) }

    }

}
