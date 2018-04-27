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


    }

}
