/**
 * Author : Henoc SESE
 */
import com.google.gson.annotations.SerializedName

/**
 * Represent an OpenClassroom Course.
 */
data class Course(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("level") val level: Level,
    @SerializedName("description") val enabled: Boolean
)