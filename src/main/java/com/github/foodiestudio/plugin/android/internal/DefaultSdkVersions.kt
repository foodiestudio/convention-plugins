package com.github.foodiestudio.plugin.android.internal

import org.gradle.api.JavaVersion

object DefaultSdkVersions {

    val java = JavaVersion.VERSION_17

    const val minSdk: Int = 21

    const val compileSdk: Int = 34

    const val targetSdk: Int = 34
}