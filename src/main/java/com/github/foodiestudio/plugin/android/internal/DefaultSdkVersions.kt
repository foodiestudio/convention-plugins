package com.github.foodiestudio.plugin.android.internal

import org.gradle.api.JavaVersion

object DefaultSdkVersions {

    val java = JavaVersion.VERSION_11

    const val minSdk: Int = 21

    const val compileSdk: Int = 33

    const val targetSdk: Int = 33
}