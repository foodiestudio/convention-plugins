package com.github.foodiestudio.plugin.git_version

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import com.android.build.api.variant.AndroidComponentsExtension

/**
 * Task 要用于输入与输出
 */
abstract class ManifestTransformTask : DefaultTask() {

    @get:InputFile
    abstract val gitInfoFile: RegularFileProperty

    @get:InputFile
    abstract val mergedManifest: RegularFileProperty

    @get:OutputFile
    abstract val updatedManifest: RegularFileProperty

    @TaskAction
    fun transform() {
        val gitVersion = gitInfoFile.get().asFile.readText()
        var manifest = mergedManifest.get().asFile.readText()

        manifest = manifest.replace(
            "android:versionName=\"",
            "android:versionName=\"$gitVersion-"
        )
        updatedManifest.get().asFile.writeText(manifest)

    }
}