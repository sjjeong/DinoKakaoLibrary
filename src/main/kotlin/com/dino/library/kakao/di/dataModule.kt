package com.dino.library.kakao.di

import com.dino.library.kakao.AsyncType
import com.dino.library.kakao.data.KakaoCoroutineRepository
import com.dino.library.kakao.data.KakaoRepository
import com.dino.library.kakao.data.coroutine.KakaoCoroutineDataSource
import com.dino.library.kakao.data.coroutine.KakaoCoroutineRepositoryImpl
import com.dino.library.kakao.data.normal.KakaoDataSource
import com.dino.library.kakao.data.normal.KakaoRepositoryImpl
import com.dino.library.kakao.data.rx.KakaoRxDataSource
import com.dino.library.kakao.data.rx.KakaoRxRepositoryImpl
import com.dino.library.kakao.network.KakaoApi
import com.dino.library.kakao.network.KakaoCoroutineApi
import com.dino.library.kakao.network.KakaoRxApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

fun createDataModule(asyncType: AsyncType) = module {
    when (asyncType) {
        AsyncType.NORMAL -> {
            single<KakaoRepository> {
                KakaoRepositoryImpl(get())
            }
            single {
                KakaoDataSource(get())
            }
            single {
                get<Retrofit>(named(KOIN_DINO_KAKAO_LIBRARY_RETROFIT))
                    .create(KakaoApi::class.java)
            }
        }
        AsyncType.RX -> {
            single<KakaoRepository> {
                KakaoRxRepositoryImpl(get())
            }
            single {
                KakaoRxDataSource(get())
            }
            single {
                get<Retrofit>(named(KOIN_DINO_KAKAO_LIBRARY_RETROFIT))
                    .create(KakaoRxApi::class.java)
            }
        }
        AsyncType.COROUTINE -> {
            single<KakaoCoroutineRepository> {
                KakaoCoroutineRepositoryImpl(get())
            }
            single {
                KakaoCoroutineDataSource(get())
            }
            single {
                get<Retrofit>(named(KOIN_DINO_KAKAO_LIBRARY_RETROFIT))
                    .create(KakaoCoroutineApi::class.java)
            }
        }
    }

}