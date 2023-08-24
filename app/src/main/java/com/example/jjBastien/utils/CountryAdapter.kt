package com.example.jjBastien.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jjBastien.model.CountryResponseItem
import com.example.practicecountry4.databinding.ListItemBinding

class CountryAdapter(private val dataSet: MutableList<CountryResponseItem> = mutableListOf() ) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(private val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(country: CountryResponseItem){
            binding.apply {
                name.text = "${country.name}, ${country.region}"
                countryCode.text = country.code
                capital.text = country.capital
            }
        }
        }

    fun getNewData(countries:List<CountryResponseItem>){
        dataSet.clear()
        dataSet.addAll(countries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
      return  CountryViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
                false
                )
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
      return dataSet.size
    }
}