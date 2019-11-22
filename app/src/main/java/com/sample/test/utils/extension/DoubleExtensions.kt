package com.sample.test.utils.extension

import java.text.DecimalFormat

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

fun Double.format(): String {
    val df = DecimalFormat()
    df.maximumFractionDigits = 1
    return df.format(this)
}

fun Double.formatTwoDigits(): String {
    val df = DecimalFormat()
    df.maximumFractionDigits = 2
    return df.format(this)
}
