package br.edu.ifpb.pdm.retrofitpracticepdm.model

import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoIF {

//    @GET("/list")
//    suspend fun listar(): List<Endereco>

    @GET("{cep}/json")
    suspend fun getEndereco(@Path("cep") customerId: String): Endereco
}