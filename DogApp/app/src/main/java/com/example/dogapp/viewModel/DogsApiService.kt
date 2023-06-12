package com.example.dogapp2.ViewModel

import com.example.dogapp.model.DogBreed
import com.example.dogapp.viewModel.DogsAPI
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DogsApiService {
    private var retrofit: Retrofit? = null
    private val  Base_URL:String ="https://raw.githubusercontent.com/"
    private val dogApit : DogsAPI
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
            }
            return retrofit!!.create(DogsAPI::class.java)
        }

    fun getDogs():Single<List<DogBreed>>{
        return dogApit.getDogs
    }


}