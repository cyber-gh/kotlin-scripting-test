package typereaders
data class Property(
    val keyword: String = "val",
    val originName: String,
    val originJsonValue: String?,
    val name: String = originName,
    val type: String,
    val value: String = "",
    val comment: String = "",
    val isLast: Boolean = false,
    val typeId: Int = -1
)