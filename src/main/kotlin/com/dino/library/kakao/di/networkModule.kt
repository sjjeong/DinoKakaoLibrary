package com.dino.library.kakao.di

import com.dino.library.kakao.AsyncType
import com.googry.dinokakaolibrary.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_DINO_KAKAO_LIBRARY_RETROFIT = "KOIN_DINO_KAKAO_LIBRARY_RETROFIT"
const val KAKAO_URL = "https://dapi.kakao.com/"

fun createNetworkModule(asyncType: AsyncType) = module {
    when (asyncType) {
        AsyncType.NORMAL -> {
            single(named(KOIN_DINO_KAKAO_LIBRARY_RETROFIT)) {
                Retrofit.Builder()
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(KAKAO_URL)
                    .build()
            }
        }
        AsyncType.RX -> {
            single(named(KOIN_DINO_KAKAO_LIBRARY_RETROFIT)) {
                Retrofit.Builder()
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(KAKAO_URL)
                    .build()
            }
        }
        AsyncType.COROUTINE -> {
            single(named(KOIN_DINO_KAKAO_LIBRARY_RETROFIT)) {
                Retrofit.Builder()
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .baseUrl(KAKAO_URL)
                    .build()
            }
        }
    }

}

fun getOkHttpClient() =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()