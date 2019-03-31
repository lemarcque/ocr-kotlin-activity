/**
 * Author : Henoc SESE
 */
package extensions

import com.google.gson.Gson
import com.google.gson.JsonObject


/**
 * Create a JSON string with error message
 */
fun Gson.createErrorJson(status: Int, message: String):String {
    val jsonObj = JsonObject()
    jsonObj.addProperty("status", status)
    jsonObj.addProperty("message", message)
    return jsonObj.toString()
}