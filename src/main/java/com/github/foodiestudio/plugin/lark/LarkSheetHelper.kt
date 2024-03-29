package com.github.foodiestudio.plugin.lark

import com.lark.oapi.Client
import com.lark.oapi.Client.newBuilder
import com.lark.oapi.core.enums.BaseUrlEnum
import com.lark.oapi.core.response.BaseResponse
import com.lark.oapi.core.response.RawResponse
import com.lark.oapi.core.response.error.Error
import com.lark.oapi.core.token.AccessTokenType
import com.lark.oapi.core.utils.UnmarshalRespUtil
import com.lark.oapi.service.sheets.v3.model.GetSpreadsheetSheetFilterReq
import com.lark.oapi.service.sheets.v3.model.QuerySpreadsheetSheetReq
import com.lark.oapi.service.sheets.v3.model.Sheet

class LarkSheetHelper(appId: String, appSecret: String, chinaOnly: Boolean) {

    private val client: Client = newBuilder(appId, appSecret)
        .openBaseUrl(if (chinaOnly) BaseUrlEnum.FeiShu else BaseUrlEnum.LarkSuite)
        .logReqAtDebug(true)
        .build()

    /**
     * 必须有设置过筛选才能去查询范围，借助缺省筛选的方式，可以返回整个数据的集合
     */
    fun fetchSheetContentRange(spreadsheetToken: String, sheetId: String): Result<String> {
        val filterParams = GetSpreadsheetSheetFilterReq.newBuilder()
            .spreadsheetToken(spreadsheetToken)
            .sheetId(sheetId)
            .build()
        return client.sheets().spreadsheetSheetFilter().get(filterParams).let {
            if (it.code == 0) {
                Result.success(it.data.sheetFilterInfo.range)
            } else {
                Result.failure(LarkAPIError(it.error))
            }
        }
    }

    /**
     * 获取 [range] 范围内的数据
     * curl --location --request GET 'https://open.feishu.cn/open-apis/sheets/v2/spreadsheets/LS4os3xmMhVwmwt1876cJb4Xn5f/values/9d44da!A1:F20?valueRenderOption=ToString&dateTimeRenderOption=FormattedString'
     * --header 'Authorization: Bearer t-g1045ngjWQ2MHM5TNJ67LBVRHEW2RAKQS5EM73ZT'
     */
    fun fetchSheetContent(spreadsheetToken: String, range: String): Result<List<List<String>>> {
        val httpResponse: RawResponse = client.get(
            "https://open.feishu.cn/open-apis/sheets/v2/spreadsheets/$spreadsheetToken/values/$range?valueRenderOption=ToString&dateTimeRenderOption=FormattedString",
            null,
            AccessTokenType.Tenant
        )
        val result = UnmarshalRespUtil.unmarshalResp(httpResponse, DetailResp::class.java)

        return if (httpResponse.statusCode == 200 && result.code == 0) {
            Result.success(result.data.valueRange.values)
        } else {
            Result.success(emptyList())
        }
    }

    /**
     * 查询子表信息，一个数据表可以有多个子表
     */
    fun fetchSheetList(spreadsheetToken: String): Result<List<Sheet>> {
        val queryParams = QuerySpreadsheetSheetReq.newBuilder()
            .spreadsheetToken(spreadsheetToken)
            .build()
        return client.sheets().spreadsheetSheet().query(queryParams).let {
            if (it.code == 0) {
                Result.success(it.data.sheets.asList())
            } else {
                Result.failure(LarkAPIError(it.error))
            }
        }
    }
}

class LarkAPIError(val error: Error) : RuntimeException()

class ValueRange(
    val majorDimension: String,
    val values: List<List<String>>
)

class SheetContentBody(
    val revision: Int,
    val valueRange: ValueRange
)

class DetailResp : BaseResponse<SheetContentBody>()