package io.github.ovso.schedule

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun dateTest() {
        val strFormat = "November 9, 2017 4:30 PM"
        val sf = SimpleDateFormat("MMMM dd, yyyy h:mm aa", Locale.US)
        val parse = sf.parse(strFormat)
        println(parse!!.time)
        println(sf.format(parse))
    }
}