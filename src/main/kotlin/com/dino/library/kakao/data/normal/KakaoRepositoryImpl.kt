package com.dino.library.kakao.data.normal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dino.library.kakao.data.KakaoRepository
import com.dino.library.kakao.data.Resource
import com.dino.library.kakao.model.KakaoWebSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoRepositoryImpl(private val kakaoDataSource: KakaoDataSource) :
    KakaoRepository {

    override fun searchWeb(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): LiveData<Resource<KakaoWebSearchResponse>> {
        val liveResource = MutableLiveData<Resource<KakaoWebSearchResponse>>()

        liveResource.postValue(Resource.loading())
        kakaoDataSource.searchWeb(query, sort, page, size)
            .enqueue(object : Callback<KakaoWebSearchResponse> {
                override fun onFailure(call: Call<KakaoWebSearchResponse>, t: Throwable) {
                    liveResource.postValue(Resource.error(t))
                }

                override fun onResponse(
                    call: Call<KakaoWebSearchResponse>,
                    response: Response<KakaoWebSearchResponse>
                ) {
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
                                Throwable("is not successful")
                            )
                        )
                    }
                }
            })
        return liveResource
    }
}