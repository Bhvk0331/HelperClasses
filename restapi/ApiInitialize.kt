package com.app.pam.restapi

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiInitialize {

    /*
    * http://192.168.1.10/pam/public/api/
    * */
    val LOCAL_URL = "http://35.177.178.179/public/api/" // TODO: Local URL

//    val LOCAL_URL = "http://35.177.178.179/public/api/" // TODO: Local URL

//    val LIVE_URL = "http://35.177.178.179/public/api/"

    val WEATHER_LIVE_URL = "http://dataservice.accuweather.com/"

    private var retrofit: Retrofit? = null
    private lateinit var apiInIt: ApiInterface

    @Synchronized
    fun getApiInIt(): ApiInterface {
        return apiInIt
    }

    fun initialize(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(LOCAL_URL)
                .client(requestHeader)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit!!
    }

    @JvmStatic
    fun initializes(): ApiInterface {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(LOCAL_URL)
            .client(requestHeader)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiInIt = retrofit!!.create(ApiInterface::class.java)
        return apiInIt
    }

    fun initialize(baseUrl: String): ApiInterface {

        val gson = GsonBuilder()
            .setLenient()
            .create()


        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(requestHeader)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        apiInIt = retrofit!!.create(ApiInterface::class.java)
        return apiInIt
    }


    private val requestHeader: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
        }

}
