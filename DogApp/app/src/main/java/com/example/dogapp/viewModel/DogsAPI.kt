package com.example.dogapp.viewModel

import com.example.dogapp.model.DogBreed
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogsAPI {

    @get:GET("DevTides/DogsApi/master/dogs.json")   // end point
    var  getDogs : Single<List<DogBreed>>
}