pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("com.android.library").version(extra["agp.version"] as String)
        id("org.jetbrains.kotlin.android").version(extra["kotlin.version"] as String)
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "boring-plugins"

include(":example")
