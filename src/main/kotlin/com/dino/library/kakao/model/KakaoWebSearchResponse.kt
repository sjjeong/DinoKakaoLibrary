package com.dino.library.kakao.model

import com.google.gson.annotations.SerializedName

data class KakaoWebSearchResponse(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("g")
    val g: List<Any>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Document(
        @SerializedName("contents")
        val contents: String,
        @SerializedName("datetime")
        val datetime: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )

    data class Meta(
        @SerializedName("dup_count")
        val dupCount: Int,
        @SerializedName("is_end")
        val isEnd: Boolean,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}