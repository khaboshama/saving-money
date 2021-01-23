package com.khaled.savingmoney.network

import com.khaled.savingmoney.constant.Constants
import com.khaled.savingmoney.network.end_points.MoneyApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitService {

    val moneyServiceApi: MoneyApi by lazy { getRetrofit().create(MoneyApi::class.java) }

    private fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.addHeader("Authorization", "Bearer " +Constants.AUTHORIZATION_KEY)
                requestBuilder.addHeader("Content-Type", "application/json")
                return chain.proceed(requestBuilder.build())
            }
        })
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(httpClient.build())
            .build()
    }
}