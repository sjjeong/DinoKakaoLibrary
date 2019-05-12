package com.dino.library.kakao.data.normal

import com.dino.library.kakao.model.KakaoWebSearchResponse
import com.dino.library.kakao.network.KakaoApi
import retrofit2.Call

class KakaoDataSource(private val kakaoApi: KakaoApi) {

    fun searchWeb(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Call<KakaoWebSearchResponse> {
        return kakaoApi.searchWeb(query, sort, page, size)
    }

}