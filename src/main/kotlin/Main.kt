/**
 * Author : Henoc SESE
 */
import com.google.gson.Gson
import extensions.createErrorJson

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

// List of OpenClassroom's Course
val courses: List<Course> = listOf(
    Course(1, "Initiez-vous à Kotlin", Level.EASY, true),
    Course(2, "Développez une applicationn pour Android", Level.MEDIUM, true),
    Course(3, "Apprenez à programmer en Java", Level.HARD,  false)
)

/**
 * Entry point of the program
 */
fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText("Welcome to OpenClassrooms brand new server !", ContentType.Text.Plain)
            }

            get("/course/top/") {
                call.respondText(getTopCourse(), ContentType.Text.JavaScript)
            }

            get("/course/{id}") {
                val id = call.parameters["id"]?.toInt() ?: -1
                if(id != -1) {
                    call.respondText(getCourseById(id), ContentType.Text.JavaScript)
                }
            }
        }
    }.start(true)
}


/**
 * Return the course associated with the id passed in parameters
 */
fun getCourseById(id: Int):String  {
    for(course in courses) {
        if(course.id == id) return Gson().toJson(course)
    }

    // Return an error, if no course were found
   return Gson().createErrorJson(404, "Sorry ! No course were found...")
}
/**
 * Return the most popular course on the OpenClassroom website.
 */
fun getTopCourse() = Gson().toJson(courses.first())

/**
 * Return all the Course available on the OpenClassroom website.
 */
fun getAllCourse() = Gson().toJson(courses)
