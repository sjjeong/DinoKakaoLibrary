package com.dino.library.kakao.data

import androidx.lifecycle.LiveData
import com.dino.library.kakao.model.KakaoWebSearchResponse

interface KakaoCoroutineRepository {
    suspend fun searchWeb(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 10
    ) : LiveData<Resource<KakaoWebSearchResponse>>
}