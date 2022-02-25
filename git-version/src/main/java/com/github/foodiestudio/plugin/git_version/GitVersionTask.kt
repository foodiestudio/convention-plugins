package com.github.foodiestudio.plugin.git_version

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 * 调用 git 命令获取当前版本，并且输出到一个中间文件，以便别的 Task 可以读取
 */
abstract class GitVersionTask : DefaultTask() {

    @get:OutputFile
    abstract val gitVersionOutputFiles: RegularFileProperty

    @TaskAction
    fun fetchGitVersion() {
//        val process = ProcessBuilder("git", "rev-parse --short HEAD").start()
//        val error = process.errorStream.readBytes().toString()
//        if (error.isNotBlank()) {
//            println("git-version plugin error: $error")
//        }
//        val version = process.inputStream.readBytes().toString()
        gitVersionOutputFiles.get().asFile.writeText("abcde" /*TODO*/)
    }
}