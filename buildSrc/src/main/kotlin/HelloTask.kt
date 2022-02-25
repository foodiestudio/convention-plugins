import org.gradle.api.DefaultTask
import org.gradle.api.internal.project.DefaultProject
import org.gradle.api.tasks.TaskAction

/**
 * 这个 Task 类必须是可继承的
 */
abstract class HelloTask : DefaultTask() {

    @TaskAction
    fun hello() {
        println("haha ${project.parent?.name}")
    }
}