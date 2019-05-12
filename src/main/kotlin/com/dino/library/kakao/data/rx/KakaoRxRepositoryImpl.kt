package com.dino.library.kakao.data.rx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dino.library.kakao.data.KakaoRepository
import com.dino.library.kakao.data.Resource
import com.dino.library.kakao.model.KakaoWebSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class KakaoRxRepositoryImpl(private val kakaoDataSource: KakaoRxDataSource) :
    KakaoRepository {

    private val compositeDisposable by lazy { CompositeDisposable() }

    fun clear(){
        compositeDisposable.clear()
    }

    override fun searchWeb(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): LiveData<Resource<KakaoWebSearchResponse>> {
        val liveResource = MutableLiveData<Resource<KakaoWebSearchResponse>>()

        liveResource.postValue(Resource.loading())
        kakaoDataSource.searchWeb(query, sort, page, size)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({
                val body = it.body()
                if (it.isSuccessful && body != null) {
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
            }){
                liveResource.postValue(Resource.error(it))
            }.addTo(compositeDisposable)
        return liveResource
    }

}