package com.example.giphysearch

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GifViewModel(
    private val repository: Repository,
    private val applicationContext: Context
) : ViewModel() {

    val gifs: MutableLiveData<List<Data>> = MutableLiveData()

    fun searchGifs(
        query: String,
        offset: Int = 0,
        limit: Int,
        rating: String = "g",
        lang: String = "en"
    ) {
        viewModelScope.launch {
            val apiKey = applicationContext.getString(R.string.gifs_api_key)
            val response = repository.searchGifs(apiKey, query, limit, offset, rating, lang)
            gifs.postValue(response.data)
        }
    }
}