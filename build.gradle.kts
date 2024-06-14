plugins {
    `kotlin-dsl`
    `maven-publish`
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


dependencies {
    // kotlin 版本由 gradle 里的版本决定，升级 gradle 会升级对应的 kotlin 依赖
    compileOnly("com.larksuite.oapi:oapi-sdk:2.2.9")
    compileOnly("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2")
    implementation(sharedLibs.kotlin.gradlePlugin)
}

group = "com.github.foodiestudio"
version = "0.5.4"

// 为 buildSrc 里的 Plugin 创建对应的 id，这样才能在 plugins{} 中使用
gradlePlugin {
    plugins {
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