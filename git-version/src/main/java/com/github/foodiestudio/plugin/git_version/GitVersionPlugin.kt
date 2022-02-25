package com.github.foodiestudio.plugin.git_version

import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import java.io.File
import org.gradle.kotlin.dsl.register

class GitVersionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
       val versionTask =  target.tasks.register<GitVersionTask>("gitVersionProvider") {
            gitVersionOutputFiles.set(
                File(
                    target.buildDir,
                    "intermediates/gitVersionProvider/output"
                )
            )
            // 永远都需要重新执行
            outputs.upToDateWhen {
                false
            }
        }

        // 在 AGP 决定创建哪个变体后，在各种对象的值被锁定而无法修改之前执行这一新Task
        val androidComponents = target.extensions.getByType(AndroidComponentsExtension::class)

        androidComponents.onVariants { variant ->
            val manifestUpdater = target.tasks.register<ManifestTransformTask>(
                variant.name + "ManifestUpdater"
            ) {
                gitInfoFile.set(versionTask.flatMap { it.gitVersionOutputFiles })
            }
            variant.artifacts.use(manifestUpdater)
                .wiredWithFiles(
                    ManifestTransformTask::mergedManifest,
                    ManifestTransformTask::updatedManifest
                ).toTransform(SingleArtifact.MERGED_MANIFEST)

            target.tasks.register<VerifyManifestTask>(variant.name + "Verifier") {
                apkFolder.set(variant.artifacts.get(SingleArtifact.APK))
                buildArtifactsLoader.set(variant.artifacts.getBuiltArtifactsLoader())
            }

        }

    }
}