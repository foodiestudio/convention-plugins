## convention-plugins
[![](https://jitpack.io/v/foodiestudio/convention-plugins.svg)](https://jitpack.io/#foodiestudio/convention-plugins)

存放一些自定义 gradle plugin。

## Compatible Specification

| AGP Version |                             Latest Support Version                             |
|:-----------:|:------------------------------------------------------------------------------:|
|    7.4.x    | [0.3.x](https://github.com/foodiestudio/convention-plugins/releases/tag/0.3.0) |
|    8.0.x    | [0.4.x](https://github.com/foodiestudio/convention-plugins/releases/tag/0.4.0) |
|    8.1.x    | [0.5.x](https://github.com/foodiestudio/convention-plugins/releases/tag/0.5.2) |

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
            if (requested.id.id.startsWith("foodiestudio")) {
                useModule("com.github.foodiestudio:convention-plugins:$version")
            }
        }
    }
}
```
- use plugin in module
```kotlin
plugins {
    id("foodiestudio.android.application.compose") // for example
}
```

see more information in [wiki](https://github.com/foodiestudio/convention-plugins/wiki)
