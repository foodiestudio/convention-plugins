package com.github.foodiestudio.plugin.android

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.github.foodiestudio.plugin.android.internal.DefaultSdkVersions
import com.github.foodiestudio.plugin.android.internal.configureGradleManagedDevices
import com.github.foodiestudio.plugin.android.internal.configurePrintApksTask
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

@Suppress("UnstableApiUsage")
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = DefaultSdkVersions.targetSdk
                buildFeatures.buildConfig = false
                configureGradleManagedDevices(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
            dependencies {
                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))
            }
        }
    }
}