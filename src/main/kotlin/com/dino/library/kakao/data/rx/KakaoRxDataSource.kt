package com.dino.library.kakao.data.rx

import com.dino.library.kakao.model.KakaoWebSearchResponse
import com.dino.library.kakao.network.KakaoRxApi
import io.reactivex.Single
import retrofit2.Response

class KakaoRxDataSource(private val kakaoRxApi: KakaoRxApi) {

    fun searchWeb(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Single<Response<KakaoWebSearchResponse>> {
        return kakaoRxApi.searchWeb(query, sort, page, size)
    }

}