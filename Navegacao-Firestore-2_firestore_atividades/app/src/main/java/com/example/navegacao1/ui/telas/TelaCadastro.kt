package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TelaCadastro(modifier: Modifier = Modifier, onRegisterClick: () -> Unit) {

    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val mensagem by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
//    val quantidadeMaxDeDigitos = 3
//    val regexApenasDigitos = Regex("^\\d*\$")

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Cadastro", textAlign = TextAlign.Center)
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") })
//        Spacer(modifier = Modifier.height(10.dp))
//        OutlinedTextField(value = cpf, onValueChange = { cpf = it }, label = { Text(text = "CPF") })
//        Spacer(modifier = Modifier.height(10.dp))
//        OutlinedTextField(
//            value = idade,
//            onValueChange = {
//                if (it.isDigitsOnly() && it.length <= quantidadeMaxDeDigitos) idade = it
//            },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            label = { Text(text = "Idade") }
//        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = senha, onValueChange = { senha = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text(text = "Senha") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                usuarioDAO.adicionar(Usuario(nome, senha), callback = { })
                onRegisterClick()
            }
        }) {
            Text(text = "Enviar")
        }
    }
}
