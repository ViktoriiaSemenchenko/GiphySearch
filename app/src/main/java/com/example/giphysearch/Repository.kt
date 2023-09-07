package com.example.giphysearch

class Repository {

    suspend fun searchGifs(
        apiKey: String,
        query: String,
        limit: Int,
        offset: Int,
        rating: String,
        lang: String
    ): Response {
        return ApiInstance.api.searchGifs(apiKey, query, limit, offset, rating, lang)
    }
}