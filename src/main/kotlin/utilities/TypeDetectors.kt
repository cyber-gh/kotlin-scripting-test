package utilities

import com.google.gson.JsonElement

enum class KotlinDataType {
    INT, DOUBLE, STRING, BOOLEAN, LIST, CUSTOM, UNIT, UNKNOWN
}

fun KotlinDataType.ktName(): String {
    return when (this) {
        KotlinDataType.INT -> "Int"
        KotlinDataType.DOUBLE -> "Double"
        KotlinDataType.STRING -> "String"
        KotlinDataType.BOOLEAN -> "Boolean"
        KotlinDataType.LIST -> "List"
        KotlinDataType.CUSTOM -> "Any"
        KotlinDataType.UNIT -> "Unit"
        KotlinDataType.UNKNOWN -> "Any"

    }
}

fun JsonElement.getValueAsRawString() : String {

    return ""
}

fun JsonElement.getType() : KotlinDataType {
    if (isJsonPrimitive) {

        val rawStr = toString()
        if (rawStr.isAJsonString()) return KotlinDataType.STRING
        if (rawStr.mayBeAnBool()) return KotlinDataType.BOOLEAN
        val str = asString
        if (str.maybeAdDouble()) return KotlinDataType.DOUBLE
        if (str.mayBeAnInt()) return KotlinDataType.INT



    }
    if (isJsonArray) return KotlinDataType.LIST
    if (isJsonObject) return KotlinDataType.CUSTOM
    if (isJsonNull) return  KotlinDataType.UNIT

    return KotlinDataType.UNKNOWN
}

internal val digits = ('0' .. '9').toList().map { it }

fun String.mayBeAnBool() : Boolean {
    return this == "true" || this == "false"
}

fun String.mayBeAnInt() : Boolean {
    return all {
        digits.contains(it)
    }
}

fun String.maybeAdDouble() : Boolean {

    var nrDots = 0
    forEach {
        if (it == '.') nrDots++
        else if(it !in digits) return false
    }
    return nrDots == 1
}


//TODO additional check for inner characters
fun String.isAJsonString() : Boolean {
    return length >= 2 &&  first() == '\"' && last() == '\"'
}