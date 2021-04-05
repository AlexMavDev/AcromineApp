package com.alexlopezmalvaez.acronimineapp.data.repository

import com.alexlopezmalvaez.acronimineapp.data.api.AcromineClient
import com.alexlopezmalvaez.acronimineapp.data.api.SafeApiRequest

class AcromineRepository (
    private val api: AcromineClient
) : SafeApiRequest() {
    suspend fun getAbbreviationDefinitions(abbreviation: String) = apiRequest { api.requestAbbreviationDefinitions(abbreviation) }
}