package com.github.foodiestudio.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class UnffiBindingTask : DefaultTask() {

    @TaskAction
    fun build() {
        with(project.extensions.getByType(CargoDesktopExtension::class.java)) {
            val moduleFile = File(udl)
            val dir = if (moduleFile.isAbsolute) {
                moduleFile
            } else {
                File(project.projectDir, moduleFile.path)
            }
            val udlFile = dir.canonicalFile
            project.exec {
                standardOutput = System.out
                errorOutput = System.err
                workingDir = project.projectDir
                executable("uniffi-bindgen")
                args(
                    mutableListOf(
                        "generate",
                        udlFile,
                        "--language",
                        "kotlin",
                        "--out-dir",
                        "${project.buildDir}/generated/uniffi/$profile"
                    )
                )
            }
        }
    }
}