package com.example.jjBastien.network

import com.example.jjBastien.model.CountryResponse
import com.example.jjBastien.utils.COUNTRY_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET


interface ServiceApi {

    @GET(COUNTRY_ENDPOINT)
    suspend fun fetchCountries() : Response<CountryResponse>
}