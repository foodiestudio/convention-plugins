## boring-plugins
[![](https://jitpack.io/v/foodiestudio/boring-plugins.svg)](https://jitpack.io/#foodiestudio/boring-plugins)

存放一些自定义 gradle plugin。

> 主要使用 kts 来实现，然后实现类可以直接用 Kotlin 来写，主要是这样能有语法高亮。

### Quick Start

- register plugin
    ```kotlin
    // settings.gradle.kts
    pluginManagement {
        repositories {
            //...
            maven("https://jitpack.io")
        }
        resolutionStrategy {
            eachPlugin {
                if (requested.id.id == "rust-desktop") {
                    useModule("com.github.foodiestudio:boring-plugins:$version")
                }
            }
        }
    }
    ```
- use plugin in module
  ```kotlin
  plugins {
      id("rust-desktop") // for example
  }
  ```

#### rust-desktop
```kotlin
cargo {
    module = "./matcher" // Or whatever directory contains your Cargo.toml
    libName = "matcher" // Or whatever matches Cargo.toml's [package] name.
    profile = "release" // Defaults to "debug"
    udl = "./matcher/src/matcher.udl" // uniffi
}

tasks.whenTaskAdded {
    if (name == "run" || name == "packageReleaseDistributionForCurrentOS") {
        // 需要依赖对应的 binding 类
        dependsOn("uniffiBuild")
        // 需要依赖对应的 $buildDir/rust/release
        dependsOn("cargoBuild")
    }
}
```

#### lark-sheet
> 暂不支持多维表格, 并且只支持已经设置过筛选的表格

```kotlin
lark {
    client {
        // https://open.feishu.cn/document/home/event-based-messaging/create-app-request-permission
        // 应用详情界面的凭证与基础信息一栏里，可以查询到应用凭证，也就是 App ID 和 App Secret
        appId = "cli_a4e6e23e517b100c"
        appSecret = "heiFVL4VHjlH4GmfPqFYyfPfUTkbbV2H"
        // 使用飞书还是 Lark 站点，可选，默认为 true
        feishu = true
    }
    // 表格访问的 Url
    sheetUrl = "https://qznxol4xbc.feishu.cn/sheets/LS4os3xmMhVwmwt1876cJb4Xn5f?sheet=9d44da"
    // export csv file to custom folder, default path like $buildDir/lark-sheet/LS4os3xmMhVwmwt1876cJb4Xn5f/9d44da/output.csv
    exportDirectory = "./custom"
}
```

#### github-publish
内置了 `maven-publish` 的依赖

```kotlin
githubPackage {
    owner = "foodiestudio"
    credentials {
        username = rootProject.findLocalProp("github.username") ?: System.getenv("USERNAME")
        password = rootProject.findLocalProp("github.classicToken") ?: System.getenv("TOKEN")
    }
    packages {
        repo("libs-versions") {
            includeVersion = all()
        }
        repo("public") {
            includeVersion = stableOnly()
        }
    }
}
```
