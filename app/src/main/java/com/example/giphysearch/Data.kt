package com.example.giphysearch

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("data")
    val data: List<Data>
)

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("images")
    val images: Images
)

data class Images(
    @SerializedName("original")
    val original: ImageDetails
)

data class ImageDetails(
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,
    @SerializedName("height")
    val height: Int
)