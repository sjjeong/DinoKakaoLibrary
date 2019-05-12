package com.dino.library.kakao.network

import com.dino.library.kakao.model.KakaoWebSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApi {

    /**
     * https://developers.kakao.com/docs/restapi/search#웹문서-검색
     * 키	    설명          	        필수	                타입
     * query	검색을 원하는 질의어	        O	                String
     * sort	    결과 문서 정렬 방식	        X(accuracy)	        accuracy (정확도순) or recency (최신순)
     * page	    결과 페이지 번호	        X(기본 1)	1-50 사이    Integer
     * size	    한 페이지에 보여질 문서의 개수	X(기본 10)1-50 사이   Integer
     */
    @GET("v2/search/web")
    fun searchWeb(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10
    ): Call<KakaoWebSearchResponse>

}