package com.example.jjBastien.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jjBastien.network.CountryRepository
import com.example.jjBastien.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :ViewModel() {
    private val countryMutableLiveData: MutableLiveData<UIState> = MutableLiveData()
    val countryLiveData:  LiveData<UIState>  get()= countryMutableLiveData

    init{
        getCountries()
    }

    fun getCountries(){
        CoroutineScope(Dispatchers.IO).launch {
            countryRepository.getCountries().collect{ state ->
                countryMutableLiveData.postValue(state)
            }
        }
    }

}