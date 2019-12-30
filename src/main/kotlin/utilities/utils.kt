package utilities

import java.io.File
import java.io.InputStream

fun readFromFile(path: String): String {
    val inputStream: InputStream = File(path).inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}