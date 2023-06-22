package com.github.foodiestudio.plugin.github

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

/**
 * ```
 * github {
 *    owner = "foodiestudio"
 *    publishing {
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

    val publishingConfig = objects.newInstance(PublishingConfig::class.java, objects)
    val credentials = objects.newInstance(Credentials::class.java)

    lateinit var owner: String

    fun publishing(fn: Action<PublishingConfig>) {
        fn.execute(publishingConfig)
    }

    fun credentials(fn: Action<Credentials>) {
        fn.execute(credentials)
    }

}

open class Credentials {
    var username: String? = null
    var password: String? = null

    /**
     * search value from local.properties for [key]
     */
    fun localProp(key: String): String? {
//        TODO
        return "key"
    }
}

open class PublishingConfig @Inject constructor(val objects: ObjectFactory) {

    val repoMap = mutableMapOf<String, PublishRepoConfig>()

    fun repo(repoName: String, fn: Action<PublishRepoConfig>) {
        val config = objects.newInstance(PublishRepoConfig::class.java)
        fn.execute(config)
        repoMap[repoName] = config
    }
}

// TODO 配置正确的 Regex
open class PublishRepoConfig {
    lateinit var includeVersion: Regex

    fun all(): Regex = ".".toRegex()

    fun stableOnly(): Regex = "stable".toRegex()

    fun snapshotOnly(): Regex = "-SNAPSHOT\$".toRegex()
}