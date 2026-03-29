## convention-plugins
[![](https://jitpack.io/v/foodiestudio/convention-plugins.svg)](https://jitpack.io/#foodiestudio/convention-plugins)

存放一些自定义 gradle plugin。

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
    id("foodiestudio.rust.desktop") // for example
}
```

see more information in [wiki](https://github.com/foodiestudio/convention-plugins/wiki)
