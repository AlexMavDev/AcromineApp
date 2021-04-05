package com.alexlopezmalvaez.acronimineapp.data.api

import com.alexlopezmalvaez.acronimineapp.data.model.AbbreDefsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineAPIRequests {

    @GET("dictionary.py")
    suspend fun requestAbbreviationDefinitions(@Query("sf") abbreviation: String): Response<AbbreDefsResponse>
}