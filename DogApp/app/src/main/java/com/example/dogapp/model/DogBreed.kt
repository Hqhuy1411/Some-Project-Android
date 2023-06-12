package com.example.dogapp.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class DogBreed : Serializable {
    @SerializedName("id")
    var id: Int = 0;

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("life_span")
    var lifeSpan: String? = ""

    @SerializedName("origin")
    var origin: String? = ""

    @SerializedName("url")
    var url: String? = ""


}