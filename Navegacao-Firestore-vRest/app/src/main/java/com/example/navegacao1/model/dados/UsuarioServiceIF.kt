package com.example.navegacao1.model.dados

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val PATH_TO_USERS = "usuarios"

interface UsuarioServiceIF {

    @GET(PATH_TO_USERS)
    suspend fun listar(): List<Usuario>

    @GET("58013240/json/")
    suspend fun getEndereco(): Endereco

    @GET("$PATH_TO_USERS/{id}")
    suspend fun getUsuarioById(@Path("id") id: String): Response<Usuario>

    @POST(PATH_TO_USERS)
    suspend fun criarUsuario(@Body usuario: Usuario): Usuario

    @DELETE("$PATH_TO_USERS/{id}")
    suspend fun removerUsuario(@Path("id") id: String): Response<Usuario>
}
