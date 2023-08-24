package com.example.jjBastien.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jjBastien.model.CountryResponseItem
import com.example.jjBastien.utils.CountryAdapter
import com.example.jjBastien.utils.UIState
import com.example.jjBastien.viewModel.CountryViewModel
import com.example.practicecountry4.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val countryAdapter by lazy {
        CountryAdapter()
    }
    lateinit var countryViewModel: CountryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        configureObserver()
    }

    fun configureObserver(){
        countryViewModel.countryLiveData.observe(this){state ->
            when(state){
                is UIState.Success<*> -> {
                    binding.error.visibility = View.GONE
                    binding.recyclerView.apply {
                        layoutManager = LinearLayoutManager(applicationContext)
                        countryAdapter.getNewData(state.response as List<CountryResponseItem>)
                        adapter = countryAdapter
                    }
                } else -> {}
            }

        }
    }
}