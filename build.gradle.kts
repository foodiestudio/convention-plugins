plugins {
    `kotlin-dsl`
}

dependencies {
}

// 为 buildSrc 里的 Plugin 创建对应的 id，这样才能在 plugins{} 中使用
gradlePlugin {
    plugins {
        create("rustDesktop") {
            id = "rust-desktop"
            implementationClass = "CargoDesktopPlugin"
        }
    }
}