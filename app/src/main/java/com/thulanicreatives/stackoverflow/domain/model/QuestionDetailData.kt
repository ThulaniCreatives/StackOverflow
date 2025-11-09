package com.thulanicreatives.stackoverflow.domain.model

import kotlinx.serialization.Serializable
import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class QuestionDetailData(
    val questionId: Int,
    val link: String,
    val title: String,
)


object CustomNavType {

    val QuestionDetailDataType = object : NavType<QuestionDetailData>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): QuestionDetailData? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): QuestionDetailData {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: QuestionDetailData): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: QuestionDetailData) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
