@file:Suppress("UnstableApiUsage")

package com.github.foodiestudio.plugin.android

import com.android.build.api.dsl.LibraryExtension
import com.github.foodiestudio.plugin.android.internal.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(AndroidLibraryPlugin::class)

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
            extension.buildFeatures.buildConfig = false
        }
    }
}