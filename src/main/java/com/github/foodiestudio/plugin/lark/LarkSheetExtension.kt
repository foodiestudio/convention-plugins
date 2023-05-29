package com.github.foodiestudio.plugin.lark

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory

open class LarkSheetExtension constructor(objects: ObjectFactory) {
    val client: LarkClientConfig = objects.newInstance(LarkClientConfig::class.java)
    lateinit var sheetUrl: String
    var exportDirectory = ""

    fun client(fn: Action<LarkClientConfig>) {
        fn.execute(client)
    }
}

open class LarkClientConfig {
    lateinit var appId: String
    lateinit var appSecret: String
    var feishu = true
}