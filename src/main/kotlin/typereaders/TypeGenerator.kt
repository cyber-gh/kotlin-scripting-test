package typereaders

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import utilities.getType
import utilities.ktName
import java.io.File

class TypeGenerator(
    private val outputFilePath: String = "src/main/generated/") {


    fun generateFlatType(jsonStr: String) : KotlinDataClass {
        val parser = JsonParser()
        val gson : JsonObject = parser.parse(jsonStr).asJsonObject

        val entries = gson.entrySet()
        val properties = entries.mapIndexed { index, mutableEntry ->
            Property(
                originName = mutableEntry.key,
                originJsonValue = mutableEntry.value.toString(),
                type = mutableEntry.value.getType().ktName(),
                isLast = index == entries.size - 1
            )
        }

        val dataClass = KotlinDataClass(properties = properties)

        return dataClass
    }

    fun outputFlatType(jsonStr: String, className: String = "GeneratedClass"){
        val dataClass = generateFlatType(jsonStr)
        dataClass.name = className
        val fileName = outputFilePath + "${dataClass.name}.kt"
        val file = File(fileName)

        file.printWriter().use {
            it.print(dataClass.generateCompleteSourceCode())
        }
    }
}