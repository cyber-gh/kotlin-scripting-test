
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import typereaders.TypeGenerator
import utilities.getType
import utilities.readFromFile
import java.io.FileReader

val jsonPath = "src/main/resources/simpleJSON.json"

fun main() {
    val typeGenerator = TypeGenerator()
    val jsonRaw = readFromFile(jsonPath)

    typeGenerator.outputFlatType(jsonRaw)



}















//val parser = JsonParser()
//val gson : JsonObject = parser.parse(FileReader(jsonPath)).asJsonObject
//
//
//gson.entrySet().forEach {
//    println(it.toString() + " is of type " + it.value.getType())
////        println(it.value.toString())
//}
