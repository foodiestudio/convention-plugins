plugins {
    `kotlin-dsl`
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
}

group = "com.github.foodiestudio"
version = "0.0.1-SNAPSHOT"

// 为 buildSrc 里的 Plugin 创建对应的 id，这样才能在 plugins{} 中使用
gradlePlugin {
    plugins {
        create("rustDesktop") {
            id = "rust-desktop"
            implementationClass = "com.github.foodiestudio.plugin.rust.CargoDesktopPlugin"
        }
    }
}