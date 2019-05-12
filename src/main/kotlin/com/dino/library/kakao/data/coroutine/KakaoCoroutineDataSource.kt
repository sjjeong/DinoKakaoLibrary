package com.dino.library.kakao.data.coroutine

import com.dino.library.kakao.model.KakaoWebSearchResponse
import com.dino.library.kakao.network.KakaoCoroutineApi
import kotlinx.coroutines.Deferred
import retrofit2.Response

class KakaoCoroutineDataSource(private val kakaoCoroutineApi: KakaoCoroutineApi) {

    fun searchWeb(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Deferred<Response<KakaoWebSearchResponse>> {
        return kakaoCoroutineApi.searchWeb(query, sort, page, size)
    }

}