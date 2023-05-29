package com.github.foodiestudio.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class CargoDesktopPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.extensions.create(EXTENSION_NAME, CargoDesktopExtension::class.java)
        // 这里必须是 afterEvaluate，不然读取不到对应的配置值
        target.afterEvaluate {
            target.tasks.maybeCreate(TASK_CARGO_BUILD_NAME, CargoBuildTask::class.java).apply {
                group = TASK_GROUP
                description = "Build rust libray"
            }
            target.tasks.maybeCreate(TASK_UNFFI_BINDING_NAME, UnffiBindingTask::class.java).apply {
                group = TASK_GROUP
                description = "Generate UnffiBinding class"
            }
        }
    }

    companion object {
        const val EXTENSION_NAME = "cargo"

        const val TASK_GROUP = "rust"
        const val TASK_CARGO_BUILD_NAME = "cargoBuild"
        const val TASK_UNFFI_BINDING_NAME = "uniffiBuild"
    }
}