package typereaders

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import utilities.*
import java.io.File

class TypeGenerator(
    private val outputFilePath: String = "src/main/generated/") {


    private fun generateFlatType(jsonStr: String, typeGeneratorConfiguration: TypeGeneratorConfiguration = TypeGeneratorConfiguration()) : KotlinDataClass {
        val parser = JsonParser()
        val gson : JsonObject = parser.parse(jsonStr).asJsonObject

        val entries = gson.entrySet()
        val properties = entries.mapIndexed { index, mutableEntry ->
            val type = mutableEntry.value.getType()
            var underlyingTYpe = KotlinDataType.UNKNOWN

            if (type == KotlinDataType.LIST) {
                underlyingTYpe = mutableEntry.value.typeOfListElements()
            }

            val isComposite = underlyingTYpe.isPrimitive()

            Property(
                keyword = typeGeneratorConfiguration.keyWord.toStr(),
                originName = mutableEntry.key,
                originJsonValue = mutableEntry.value.toString(),
                type = mutableEntry.value.getType().ktName(),
                isLast = index == entries.size - 1,
                isList = isComposite,
                underlyingType = underlyingTYpe.ktName(),
                nullable = typeGeneratorConfiguration.nullable
            )
        }

        val dataClass = KotlinDataClass(properties = properties)

        return dataClass
    }

    fun outputFlatType(jsonStr: String, className: String = "GeneratedClass"){
        outputFlatType(jsonStr, TypeGeneratorConfiguration(className))
    }

    fun outputFlatType(jsonStr: String, typeGeneratorConfiguration: TypeGeneratorConfiguration) {
        val dataClass = generateFlatType(jsonStr)
        dataClass.name = typeGeneratorConfiguration.className
        val fileName = outputFilePath + "${dataClass.name}.kt"
        val file = File(fileName)

        file.printWriter().use {
            it.print(dataClass.generateCompleteSourceCode())
        }
    }
}

data class TypeGeneratorConfiguration(
    val className: String = "GeneratedClass",
    val keyWord: PropertyKeyWord = PropertyKeyWord.VAL,
    val nullable: Boolean = false
)