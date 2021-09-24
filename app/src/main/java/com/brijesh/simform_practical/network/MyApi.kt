package com.brijesh.simform_practical.network


import com.brijesh.simform_practical.model.RandomUserResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("api?results=100")
    suspend fun getAllUserDataList(): Response<RandomUserResponse>

    companion object {

        private const val BASE_URL = "https://randomuser.me/"

        operator fun invoke(): MyApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder().also {
                /* val logging = HttpLoggingInterceptor()
                 logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                 client.addInterceptor(logging)*/
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
}