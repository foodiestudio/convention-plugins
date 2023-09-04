package com.github.foodiestudio.plugin.android

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.github.foodiestudio.plugin.android.internal.DefaultSdkVersions
import com.github.foodiestudio.plugin.android.internal.configureGradleManagedDevices
import com.github.foodiestudio.plugin.android.internal.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                // 这里不需要指定 plugin 的版本
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = DefaultSdkVersions.targetSdk
                configureGradleManagedDevices(this)
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configurePrintApksTask(this)
            }
        }
    }
}

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>
) {
    commonExtension.apply {
        compileSdk = DefaultSdkVersions.compileSdk

        defaultConfig {
            minSdk = DefaultSdkVersions.minSdk
        }

        compileOptions {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = DefaultSdkVersions.java
            targetCompatibility = DefaultSdkVersions.java
            isCoreLibraryDesugaringEnabled = true
        }
    }

    configureKotlin()

    dependencies {
        add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:2.0.3")
    }
}

/**
 * Configure base Kotlin options for JVM (non-Android)
 */
internal fun Project.configureKotlinJvm() {
    extensions.configure<JavaPluginExtension> {
        // Up to Java 11 APIs are available through desugaring
        // https://developer.android.com/studio/write/java11-minimal-support-table
        sourceCompatibility = DefaultSdkVersions.java
        targetCompatibility = DefaultSdkVersions.java
    }

    configureKotlin()
}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            // Set JVM target to 11
            jvmTarget = DefaultSdkVersions.java.toString()
            // Treat all Kotlin warnings as errors (disabled by default)
            // Override by setting warningsAsErrors=true in your ~/.gradle/gradle.properties
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )
        }
    }
}