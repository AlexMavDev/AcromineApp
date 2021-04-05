package com.alexlopezmalvaez.acronimineapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AcromineClient: AcromineAPIRequests {

    companion object{
            var BASE_URL : String = "https://www.nactem.ac.uk/software/acromine/"
        operator fun invoke() : AcromineClient {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(UnsafeOkHttpClient.unsafeOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AcromineClient::class.java)
        }
    }

}