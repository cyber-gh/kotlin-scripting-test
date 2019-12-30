import com.google.gson.Gson


data class GeneratedClass (
	val names: List<String>
){
    companion object {
        fun readFromSource(jsonStr: String) : GeneratedClass {
            val out = Gson().fromJson(jsonStr, GeneratedClass::class.java)
            return out
        }
    }
}