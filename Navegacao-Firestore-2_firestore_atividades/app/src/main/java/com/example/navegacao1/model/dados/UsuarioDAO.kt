package com.example.navegacao1.model.dados

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

//classe que pega dados do Firestore
class UsuarioDAO {

    private val db = Firebase.firestore

    fun buscar(callback: (List<Usuario>) -> Unit) {
        db.collection("usuarios").get()
            .addOnSuccessListener { document ->
                val usuarios = document.toObjects<Usuario>()
                callback(usuarios)
            }
            .addOnFailureListener {
                callback(emptyList())
            }
    }

    fun buscarPorNome(nome: String, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").whereEqualTo("nome", nome).get()
            .addOnSuccessListener { document ->
                if (!document.isEmpty) {
                    val usuario = document.documents[0].toObject<Usuario>()
                    callback(usuario)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }

    fun buscarPorId(id: String, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").whereEqualTo("id", id).get()
            .addOnSuccessListener { document ->
                if (!document.isEmpty) {
                    val usuario = document.documents[0].toObject<Usuario>()
                    callback(usuario)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }


    fun adicionar(usuario: Usuario, callback: (Usuario?) -> Unit) {
        db.collection("usuarios").add(usuario)
            .addOnSuccessListener { documentReference ->
                if (documentReference != null) {
                    callback(usuario)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }

}
