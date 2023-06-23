plugins {
    `kotlin-dsl`
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    jvmToolchain(11)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xallow-result-return-type"
    }
}

dependencies {
    // kotlin 版本由 gradle 里的版本决定，升级 gradle 会升级对应的 kotlin 依赖
    implementation(kotlin("stdlib"))
    implementation("com.larksuite.oapi:oapi-sdk:2.0.19")
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.1")
}

group = "com.github.foodiestudio"
version = "0.2.0"

// 为 buildSrc 里的 Plugin 创建对应的 id，这样才能在 plugins{} 中使用
gradlePlugin {
    plugins {
        create("rustDesktop") {
            id = "rust-desktop"
            implementationClass = "com.github.foodiestudio.plugin.rust.CargoDesktopPlugin"
        }
        create("larkSheet") {
            id = "lark-sheet"
            implementationClass = "com.github.foodiestudio.plugin.lark.LarkSheetPlugin"
        }
        create("githubPublish") {
            id = "github-publish"
            implementationClass = "com.github.foodiestudio.plugin.github.GithubPublishPlugin"
        }
    }
}