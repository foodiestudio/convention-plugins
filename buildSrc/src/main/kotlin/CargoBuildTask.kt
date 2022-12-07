import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * 执行 cargo build
 * TODO 完善对 build 的参数支持
 */
abstract class CargoBuildTask : DefaultTask() {

    @TaskAction
    fun build() {
        with(project.extensions.getByType(CargoDesktopExtension::class.java)) {
            val moduleFile = File(module)
            val dir = if (moduleFile.isAbsolute) {
                moduleFile
            } else {
                File(project.projectDir, moduleFile.path)
            }
            val arguments = mutableListOf(COMMAND_BUILD)
            if (profile != "debug") {
                arguments.add("--$profile")
            }
            project.exec {
                standardOutput = System.out
                errorOutput = System.err
                workingDir = dir.canonicalFile
                executable(COMMAND_CARGO)
                args(arguments)

            }

            val cargoOutputDir = File(dir, "target/$profile").canonicalFile
            val destDir =if (targetDirectory != null) {
                File(targetDirectory!!)
            } else {
                File(project.buildDir, "rust/$profile")
            }
            destDir.mkdirs()
            project.copy {
                from(cargoOutputDir)
                into(destDir)
                // linux
                include("lib${libName}.so")
                // macOS
                include("lib${libName}.dylib")
                // windows
                include("${libName}.dll")
            }
        }
    }

    companion object {
        const val COMMAND_CARGO = "cargo"

        const val COMMAND_BUILD = "build"
    }
}