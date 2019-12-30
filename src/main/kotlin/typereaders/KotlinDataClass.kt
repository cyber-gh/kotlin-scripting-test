package typereaders

data class KotlinDataClass(
    val id: Int = -1,
    val name: String = "GeneratedClass",
    val properties: List<Property>
) {
    fun generateCode() : String {
        val nameLine = "\ndata class ${name} ("
        val endLine = ")"
        val propertiesLines = properties.map {
            "\t${it.keyword} ${it.name}: ${it.type}" +
                    if (!it.isLast) "," else ""
        }.toList()

        val lines = mutableListOf<String>(nameLine) + propertiesLines + endLine

        return lines.joinToString("\n")
    }
}