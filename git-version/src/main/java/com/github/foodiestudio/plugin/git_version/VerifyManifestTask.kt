package com.github.foodiestudio.plugin.git_version

import com.android.build.api.variant.BuiltArtifactsLoader
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.lang.RuntimeException

abstract class VerifyManifestTask : DefaultTask() {

    @get:InputFiles
    abstract val apkFolder: DirectoryProperty

    /**
     * 用于从元数据文件中加载 BuiltArtifacts 对象，元数据文件描述了 APK 目录下的文件信息。
     * BuiltArtifactsLoader 抽象了识别每个 APK 及其属性的过程
     */
    @get:Internal
    abstract val buildArtifactsLoader: Property<BuiltArtifactsLoader>

    @TaskAction
    fun verify() {
        val builtArtifacts = buildArtifactsLoader.get().load(apkFolder.get())
            ?: throw RuntimeException("cannot load APKs")

        if (builtArtifacts.elements.size != 1) {
            throw RuntimeException("Excepted one APK!")
        }
        val apk = File(builtArtifacts.elements.single().outputFile).toPath()
        println("Insert code to verify manifest file in $apk")
        println("Success ")
    }
}