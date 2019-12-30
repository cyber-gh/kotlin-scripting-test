
import org.junit.Test
import typereaders.TypeGenerator
import utilities.readFromFile

class TypeGeneratorTests {

    private val workingDir = "src/test/resources/"

    @Test
    fun testGeneratesBasicFlatType() {
        val inJsonFilePath = workingDir + "basicFlatType.json"
        val outGeneratedClassPath = workingDir +  "BasicFlatTypeOutput"
        val rawStr = readFromFile(inJsonFilePath)
        val typeGenerator = TypeGenerator(workingDir)

        val genClassName = "BasicFlatTypeGenerated"
        typeGenerator.outputFlatType(rawStr, genClassName)

        assert(
            readFromFile(outGeneratedClassPath) == readFromFile("$workingDir$genClassName.kt")
        )

    }
}