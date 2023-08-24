package com.example.jjBastien.network

import com.example.jjBastien.utils.UIState
import kotlinx.coroutines.flow.Flow

interface CountryRepository {

   suspend fun getCountries(): Flow<UIState>
}