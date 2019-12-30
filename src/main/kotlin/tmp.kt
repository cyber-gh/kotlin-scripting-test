import com.google.gson.Gson

data class GeneratedClass (
    val integer: Int,
    val decimal: Double,
    val boolean: Boolean,
    val string: String
)
{
    companion object {
        fun readFromSource(jsonStr: String) : GeneratedClass {
            val out = Gson().fromJson(jsonStr, GeneratedClass::class.java)
            return out
        }
    }
}