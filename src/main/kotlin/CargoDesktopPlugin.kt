import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * TODO
 * - 支持增量构建，如果 Rust 工程下源代码没有发生变化的话，避免重新编译
 */
class CargoDesktopPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create(EXTENSION_NAME, CargoDesktopExtension::class.java)
        // 这里必须是 afterEvaluate，不然读取不到对应的配置值
        target.afterEvaluate {
            target.tasks.maybeCreate(TASK_NAME, CargoBuildTask::class.java).apply {
                group = TASK_GROUP
                description = "Build rust libray"
            }
        }
    }

    companion object {
        const val EXTENSION_NAME = "cargo"

        const val TASK_GROUP = "rust"
        const val TASK_NAME = "cargoBuild"
    }
}