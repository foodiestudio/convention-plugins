open class CargoDesktopExtension {
    lateinit var module: String
    lateinit var libName: String
    var profile: String = "debug"
    var targetDirectory: String? = null

    // used with uniffi, not cargo
    lateinit var udl: String
}