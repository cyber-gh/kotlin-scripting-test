
import org.junit.Test
import typereaders.TypeGenerator
import utilities.readFromFile

class TypeGeneratorTests {

    private val workingDir = "src/test/resources/"
    private val typeGenerator = TypeGenerator(workingDir)

    @Test
    fun testGeneratesBasicFlatType() {
        val testName = "BasicFlatType"
        val inJsonFilePath = "$workingDir$testName.json"
        val outGeneratedClassPath = workingDir + testName + "Output"// "BasicFlatTypeOutput"
        val rawStr = readFromFile(inJsonFilePath)


        val genClassName = testName + "Generated"// "BasicFlatTypeGenerated"
        typeGenerator.outputFlatType(rawStr, genClassName)

        assert(
            readFromFile(outGeneratedClassPath) == readFromFile("$workingDir$genClassName.kt")
        )

    }
}