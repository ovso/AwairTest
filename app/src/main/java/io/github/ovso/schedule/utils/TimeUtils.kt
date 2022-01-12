package io.github.ovso.schedule.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {
    fun stringToSystemMillis(strFormat: String): Long {
        val sf = SimpleDateFormat("MMMM dd, yyyy h:mm aa", Locale.US)
        val parse = sf.parse(strFormat)
        return parse!!.time
    }
}