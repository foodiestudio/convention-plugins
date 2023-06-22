package com.github.foodiestudio.plugin.lark

import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory

/**
 * ```kotlin
 * lark {
 *     client {
 *         // https://open.feishu.cn/document/home/event-based-messaging/create-app-request-permission
 *         // 应用详情界面的凭证与基础信息一栏里，可以查询到应用凭证，也就是 App ID 和 App Secret
 *         appId = "cli_a4e6e23e517b100c"
 *         appSecret = "heiFVL4VHjlH4GmfPqFYyfPfUTkbbV2H"
 *         // 使用飞书还是 Lark 站点，可选，默认为 true
 *         feishu = true
 *     }
 *     // 表格访问的 Url
 *     sheetUrl = "https://qznxol4xbc.feishu.cn/sheets/LS4os3xmMhVwmwt1876cJb4Xn5f?sheet=9d44da"
 *     // export csv file to custom folder, default path like $buildDir/lark-sheet/LS4os3xmMhVwmwt1876cJb4Xn5f/9d44da/output.csv
 *     exportDirectory = "./custom"
 * }
 * ```
 */
open class LarkSheetExtension(objects: ObjectFactory) {
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