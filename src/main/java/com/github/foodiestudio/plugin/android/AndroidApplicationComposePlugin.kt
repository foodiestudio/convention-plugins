@file:Suppress("UnstableApiUsage")

package com.github.foodiestudio.plugin.android

import com.android.build.api.dsl.ApplicationExtension
import com.github.foodiestudio.plugin.android.internal.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

/**
 * 带 compose 的 android application plugin
 */
class AndroidApplicationComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(AndroidApplicationPlugin::class)

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}

