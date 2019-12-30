
import typereaders.TypeGenerator
import utilities.readFromFile

val jsonPath = "src/main/resources/simpleJSON.json"



fun main() {
    val typeGenerator = TypeGenerator()
    val jsonRaw = readFromFile(jsonPath)

    typeGenerator.outputFlatType(jsonRaw)

    val tst = GeneratedClass.readFromSource(jsonRaw)

    println(tst)
    print( tst.string)



}















//val parser = JsonParser()
//val gson : JsonObject = parser.parse(FileReader(jsonPath)).asJsonObject
//
//
//gson.entrySet().forEach {
//    println(it.toString() + " is of type " + it.value.getType())
////        println(it.value.toString())
//}
