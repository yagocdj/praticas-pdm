package br.edu.ifpb.pdm.retrofitpracticepdm.model

data class Endereco(
    val cep: String = "",
    val logradouro: String = "",
    val complemento: String = "",
    val unidade: String = "",
    val bairro: String = "",
    val localidade: String = "",
    val uf: String = "",
    val ibge: String = "",
    val gia: String = "",
    val ddd: String = "",
    val siafi: String = ""
)