package com.github.foodiestudio.plugin.github

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import java.io.FileInputStream
import java.util.*
import javax.inject.Inject

/**
 * ```kotlin
 * githubPackage {
 *    owner = "foodiestudio"
 *    credentials {
 *        username = rootProject.findLocalProp("github.username") ?: System.getenv("USERNAME")
 *        password = rootProject.findLocalProp("github.classicToken") ?: System.getenv("TOKEN")
 *    }
 *    packages {
 *      repo("sugar") {
 *         includeVersion = all()
 *      }
 *      repo("public") {
 *         includeVersion = stableOnly()
 *      }
 *    }
 * }
 * ```
 */
open class GithubPublishExtension(objects: ObjectFactory) {

    val packagesConfig = objects.newInstance(PackagesConfig::class.java, objects)
    val credentials = objects.newInstance(Credentials::class.java)

    lateinit var owner: String

    fun packages(fn: Action<PackagesConfig>) {
        fn.execute(packagesConfig)
    }

    fun credentials(fn: Action<Credentials>) {
        fn.execute(credentials)
    }

}

open class Credentials {
    var username: String? = null
    var password: String? = null

    fun Project.findLocalProp(key: String): String? {
        val localFile = file("local.properties")
        if (localFile.exists()) {
            val localProp = Properties()
            localProp.load(FileInputStream(localFile))
            return localProp[key] as String?
        }
        return null
    }
}

open class PackagesConfig @Inject constructor(private val objects: ObjectFactory) {

    val repoMap = mutableMapOf<String, PublishRepoConfig>()

    fun repo(repoName: String, fn: Action<PublishRepoConfig>) {
        val config = objects.newInstance(PublishRepoConfig::class.java)
        fn.execute(config)
        repoMap[repoName] = config
    }
}

open class PublishRepoConfig {
    lateinit var includeVersion: Regex

    /**
     * accept all not empty version
     */
    fun all(): Regex = "(.)+".toRegex()

    /**
     * valid examples:
     *
     * ```
     * 1.0.0
     * 2023.00.10
     * ```
     * invalid examples:
     *
     * ```
     * 1.0.0-rc.1
     * 2023.00.10-SNAPSHOT
     * ```
     */
    fun stableOnly(): Regex = "[0-9]+(.[0-9]+)+".toRegex()

    fun snapshotOnly(): Regex = "(.)+-SNAPSHOT\$".toRegex()
}