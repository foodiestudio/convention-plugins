pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
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
