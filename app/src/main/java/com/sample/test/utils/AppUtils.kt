package com.sample.test.utils

import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.zip.DataFormatException
import java.util.zip.Inflater

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

/*
* Decode compress algorithm code inspired from
* https://dzone.com/articles/how-compress-and-uncompress
*/

@Throws(UnsupportedEncodingException::class, DataFormatException::class)
fun decodeToString(input: String): String {
    val byteArray = android.util.Base64.decode(input, android.util.Base64.DEFAULT)
    val inflater = Inflater()
    inflater.setInput(byteArray)
    val os = ByteArrayOutputStream(byteArray.size)
    val buffer = ByteArray(1024)
    while (!inflater.finished()) {
        val c = inflater.inflate(buffer)
        os.write(buffer, 0, c)
    }
    try {
        os.close()
    } catch (e: IOException) {
        Timber.e(e)
    }
    val output = os.toByteArray()
    return String(output, Charset.forName("UTF-8"))
}
