
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import typereaders.TypeGenerator
import utilities.readFromFile

@RunWith(Parameterized::class)
class TypeGeneratorTests(
    val testName : String
) {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : Collection<String> {
            return listOf(
                "BasicFlatType",
                "ListFlatType",
                "CombinedFlatType"
            )
        }
    }

    private val workingDir = "src/test/resources/"
    private val typeGenerator = TypeGenerator(workingDir)

    @Test
    fun testGeneratesBasicFlatType() {
        //val testName = "ListFlatType"
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