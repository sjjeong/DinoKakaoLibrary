package com.dino.library.kakao.data.coroutine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dino.library.kakao.data.KakaoCoroutineRepository
import com.dino.library.kakao.data.Resource
import com.dino.library.kakao.model.KakaoWebSearchResponse

class KakaoCoroutineRepositoryImpl(private val kakaoDataSource: KakaoCoroutineDataSource) :
    KakaoCoroutineRepository {

    override suspend fun searchWeb(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): LiveData<Resource<KakaoWebSearchResponse>> {
        val liveResource = MutableLiveData<Resource<KakaoWebSearchResponse>>()

        liveResource.postValue(Resource.loading())
        val response = kakaoDataSource.searchWeb(query, sort, page, size).await()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            liveResource.postValue(
                Resource.success(
                    body
                )
            )
        } else {
            liveResource.postValue(
                Resource.error(
                    Throwable("kakao web search fail")
                )
            )
        }
        return liveResource
    }
}