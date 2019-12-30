package typereaders

data class KotlinDataClass(
    val id: Int = -1,
    var name: String = "GeneratedClass",
    val properties: List<Property>
) {
    fun generateCode() : String {
        val nameLine = "\ndata class ${name} ("
        val endLine = ")"
        val propertiesLines = properties.map {
            val typeDescriptor = if(it.isList) "List<${it.underlyingType}>" else it.type +
                                 if(it.nullable) "?" else ""

            "\t${it.keyword} ${it.name}: ${typeDescriptor}" +
                    if (!it.isLast) "," else ""
        }.toList()

        val definitionCodeLines = mutableListOf<String>(nameLine) + propertiesLines + endLine



        return definitionCodeLines.joinToString("\n")
    }

    fun getImportStatements(): String {
        val lst = listOf("import com.google.gson.Gson")
        return lst.joinToString("\n") + "\n"
    }

    fun generateCompleteSourceCode() : String {
        val importStatements = getImportStatements()
        val definitionCode = generateCode()
        val implementationCode = generateImplementationOfReader()

        return importStatements + definitionCode + implementationCode
    }

    fun generateImplementationOfReader() : String {
        return "{\n" +
                "    companion object {\n" +
                "        fun readFromSource(jsonStr: String) : ${name} {\n" +
                "            val out = Gson().fromJson(jsonStr, ${name}::class.java)\n" +
                "            return out\n" +
                "        }\n" +
                "    }\n" +
                "}"
    }


}