package br.edu.ifpb.pdm.retrofitpracticepdm.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://viacep.com.br/ws/"

    val enderecoService: EnderecoIF by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EnderecoIF::class.java)
    }
}