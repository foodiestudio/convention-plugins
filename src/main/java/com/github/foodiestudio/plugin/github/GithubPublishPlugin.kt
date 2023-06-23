package com.github.foodiestudio.plugin.github

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin

class GithubPublishPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply(MavenPublishPlugin::class.java)
        target.extensions.create(extensionName, GithubPublishExtension::class.java)

        target.afterEvaluate {
            val githubConfig = target.extensions.getByType(GithubPublishExtension::class.java)

            target.extensions.configure(PublishingExtension::class.java) {
                repositories {
                    githubConfig.packagesConfig.repoMap
                        .filterValues { it.includeVersion.matches(target.version.toString()) }
                        .forEach { (repoName, _) ->
                            maven {
                                name = repoName
                                setUrl("https://maven.pkg.github.com/${githubConfig.owner}/$repoName")
                                credentials {
                                    username = githubConfig.credentials.username
                                    password = githubConfig.credentials.password
                                }
                            }
                        }
                }
            }
        }
    }

    companion object {
        const val extensionName = "githubPackage"
    }

}

