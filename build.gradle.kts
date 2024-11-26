plugins {
    `kotlin-dsl`
    `maven-publish`
    id("com.github.gmazzo.buildconfig") version "4.1.2"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xallow-result-return-type"
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


buildConfig {
    buildConfigField("String", "COMPOSE_COMPILER_VERSION", "\"${sharedLibs.versions.compose.compiler.get()}\"")
    buildConfigField("String", "COMPOSE_BOM", provider { "\"${sharedLibs.compose.bom.get()}\"" })
}

dependencies {
    // kotlin 版本由 gradle 里的版本决定，升级 gradle 会升级对应的 kotlin 依赖
    compileOnly("com.larksuite.oapi:oapi-sdk:2.0.31")
    compileOnly("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2")
    implementation(sharedLibs.android.gradlePlugin)
    implementation(sharedLibs.kotlin.gradlePlugin)
}

group = "com.github.foodiestudio"
version = "0.5.4"

// 为 buildSrc 里的 Plugin 创建对应的 id，这样才能在 plugins{} 中使用
gradlePlugin {
    plugins {
        create("androidApplicationCompose") {
            id = "foodiestudio.android.application.compose"
            implementationClass = "com.github.foodiestudio.plugin.android.AndroidApplicationComposePlugin"
        }
        create("androidApplication") {
            id = "foodiestudio.android.application"
            implementationClass = "com.github.foodiestudio.plugin.android.AndroidApplicationPlugin"
        }
        create("androidLibrary") {
            id = "foodiestudio.android.library"
            implementationClass = "com.github.foodiestudio.plugin.android.AndroidLibraryPlugin"
        }
        create("androidLibraryCompose") {
            id = "foodiestudio.android.library.compose"
            implementationClass = "com.github.foodiestudio.plugin.android.AndroidLibraryComposePlugin"
        }
        // ================
        create("rustDesktop") {
            id = "foodiestudio.rust.desktop"
            implementationClass = "com.github.foodiestudio.plugin.rust.CargoDesktopPlugin"
        }
        create("larkSheet") {
            id = "foodiestudio.lark.sheet"
            implementationClass = "com.github.foodiestudio.plugin.lark.LarkSheetPlugin"
        }
        create("githubPublish") {
            id = "foodiestudio.github.publish"
            implementationClass = "com.github.foodiestudio.plugin.github.GithubPublishPlugin"
        }
    }
}