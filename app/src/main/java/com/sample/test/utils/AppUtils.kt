package com.sample.test.utils

import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.util.zip.Inflater

/**
 * Created by SangiliPandian C on 22-11-2019.
 */

@Throws(IOException::class)
fun InputStream.readAllBytes(): ByteArray {
    val bufLen = 4 * 0x400 // 4KB
    val buf = ByteArray(bufLen)
    var readLen: Int
    ByteArrayOutputStream().use { o ->
        this.use { i ->
            while (i.read(buf, 0, bufLen).also { readLen = it } != -1)
                o.write(buf, 0, readLen)
        }
        return o.toByteArray()
    }
}

fun decodeToString(byteArray: ByteArray): String {
    val inflater = Inflater()
    inflater.setInput(byteArray)
    val os = ByteArrayOutputStream(byteArray.size)
    val buffer = ByteArray(1024)
    while (!inflater.finished()) {
        val c = inflater.inflate(buffer)
        os.write(buffer, 0, c)
    }
    os.close()
    val output = os.toByteArray()
    return String(output, Charset.forName("UTF-8"))
}
