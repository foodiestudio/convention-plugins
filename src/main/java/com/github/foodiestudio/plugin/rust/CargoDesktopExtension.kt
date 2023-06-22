package com.github.foodiestudio.plugin.rust

/**
 * ```kotlin
 * cargo {
 *     module = "./matcher" // Or whatever directory contains your Cargo.toml
 *     libName = "matcher" // Or whatever matches Cargo.toml's [package] name.
 *     profile = "release" // Defaults to "debug"
 *     udl = "./matcher/src/matcher.udl" // uniffi
 * }
 * ```
 */
open class CargoDesktopExtension {
    lateinit var module: String
    lateinit var libName: String
    var profile: String = "debug"
    var targetDirectory: String? = null

    // used with uniffi, not cargo
    lateinit var udl: String
}