plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("rust-desktop") // from buildSrc
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}

cargo {
    module = "./matcher"       // Or whatever directory contains your Cargo.toml
    libName = "matcher"         // Or whatever matches Cargo.toml's [package] name.
    profile = "release"         // Defaults to "debug"
    targetDirectory = "$buildDir/cargoBuild/" // Defaults to build/rust/
}
//
//tasks.whenTaskAdded {
//    if (name == "clean") {
//        dependsOn("hello")
//    }
//}