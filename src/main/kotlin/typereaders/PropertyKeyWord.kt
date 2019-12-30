package typereaders

enum class PropertyKeyWord {
    VAL, VAR
}

fun PropertyKeyWord.toStr() : String = when(this) {
    PropertyKeyWord.VAL -> "val"
    PropertyKeyWord.VAR -> "var"
}