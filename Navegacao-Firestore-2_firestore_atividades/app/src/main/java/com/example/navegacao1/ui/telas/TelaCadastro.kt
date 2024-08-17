package com.example.navegacao1.ui.telas

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TelaCadastro(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    onGoBackClick: () -> Unit
) {

    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    var mensagem by rememberSaveable { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Cadastro",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = senha, onValueChange = { senha = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(text = "Senha") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            val novoUsuario = Usuario(nome = nome, senha = senha)
            if (novoUsuario.nome.isBlank()) {
                mensagem = "Digite um nome válido"
            } else if (novoUsuario.senha.isBlank()) {
                mensagem = "Digite uma senha válida"
            } else {
                scope.launch(Dispatchers.IO) {
                    usuarioDAO.adicionar(novoUsuario, callback = { usuario ->
                        if (usuario != null) {
                            onRegisterClick()
                        }
                    })
                }
            }
        }) {
            Text(text = "Enviar")
        }
        Button(onClick = { onGoBackClick() }) {
            Text(text = "Voltar")
        }
        mensagem?.let {
            LaunchedEffect(it) {
                Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show()
                mensagem = null
            }
        }
    }
}
