package com.edvora.demo.Network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPIClient {

    lateinit var retrofit: Retrofit

    private const val BASE_URL = "https://assessment-edvora.herokuapp.com/"



    val retrofitInstance: Retrofit
        get() {
            if (!this::retrofit.isInitialized) {
//                val headersInterceptor = Interceptor { chain ->
//                    val requestBuilder = chain.request().newBuilder()
//                    requestBuilder.header("Authorization", "Bearer $token")
//                    chain.proceed(requestBuilder.build())
//                }
                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .followRedirects(true)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }


}