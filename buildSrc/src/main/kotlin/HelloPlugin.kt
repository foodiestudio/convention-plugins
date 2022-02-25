import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class HelloPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        // 等同于 target.tasks.register("hello", HelloTask::class.java)
        target.tasks.register<HelloTask>("hello")
    }
}