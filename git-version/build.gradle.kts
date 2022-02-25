plugins {
    id("java-gradle-plugin")
    id("kotlin")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(gradleKotlinDsl()) // gradleApi 对应的 kotlin 版本
    implementation("com.android.tools.build:gradle-api:7.0.4")
    testImplementation(gradleTestKit())
}

group = "com.github.foodiestudio.plugin"
version = "0.0.1-SNAPSHOT"

gradlePlugin {
    plugins {
        create("release") {
            id = "git-version"
            implementationClass = "com.github.foodiestudio.plugin.git_version.GitVersionPlugin"
        }
    }
}

