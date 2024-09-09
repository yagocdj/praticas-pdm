package com.example.navegacao1.ui.telas

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.navegacao1.R
import com.example.navegacao1.model.dados.Endereco
import com.example.navegacao1.model.dados.RetrofitClient
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException


@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    var scope = rememberCoroutineScope()

    Column(modifier = modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tela Principal")
//        var usuarios by remember { mutableStateOf<List<Usuario>>(emptyList()) }
        var endereco by remember { mutableStateOf<Endereco>(Endereco()) }

        // Campos de um usuário
        var nome by rememberSaveable { mutableStateOf("") }
        var senha by rememberSaveable { mutableStateOf("") }
        var idUsuario by rememberSaveable { mutableStateOf("") }

        // Estado do campo de senha
        var senhaVisivel by rememberSaveable { mutableStateOf(false) }

        // Adicionando os pratos a uma lista mutável (para poder removê-los da tela)
        var usuariosMutaveis = remember { mutableStateListOf<Usuario>() }
//        usuariosMutaveis.addAll(usuarios)

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") },
            placeholder = { Text(text = "Nome") }
        )
        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text(text = "Senha") },
            singleLine = true,
            placeholder = { Text(text = "Senha") },
            visualTransformation = if (senhaVisivel) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val imagem = if (senhaVisivel)
                    R.drawable.visibility else R.drawable.visibility_off

                // descrição para fins de acessibilidade
                val descricao = if (senhaVisivel) "Esconder senha" else "Mostrar senha"

                IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                    Icon(painter = painterResource(id = imagem), descricao)
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        // salvar o usuário
        Button(onClick = {
            scope.launch {
                val usuarioCriado = criarUsuario(nome, senha)

                // Limpando os campos ao criar um novo usuário
                nome = ""
                senha = ""

                usuariosMutaveis.add(usuarioCriado)
            }
        }) { Text(text = "Salvar") }

        Spacer(modifier = Modifier.height(12.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.secondary)
        Spacer(modifier = Modifier.height(12.dp))

        LaunchedEffect(Unit) {
            scope.launch {
                Log.d("principal", "aqui")
                usuariosMutaveis.addAll(getUsuarios())

                // Log.d("principal", getEndereco().logradouro)
                // endereco = getEndereco()
            }
        }

        // Text(endereco.logradouro)

        Row {
            Button(onClick = {
                scope.launch {
                    usuariosMutaveis.clear()
                    usuariosMutaveis.addAll(getUsuarios())
                }
            }) {
                Text("Carregar")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Button(onClick = { onLogoffClick() }) {
                Text("Sair")
            }
        }

        // Buscar usuário por ID
        Row {
            OutlinedTextField(value = idUsuario,
                onValueChange = { if (it.isDigitsOnly()) idUsuario = it },
                label = { Text(text = "ID do usuário") },
                placeholder = { Text(text = "ID do usuário") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            try {
                                val resposta = getUsuarioById(idUsuario)

                                if (resposta.isSuccessful) {
                                    val corpo = resposta.body()
                                    if (corpo != null) {
                                        usuariosMutaveis.clear()
                                        usuariosMutaveis.add(corpo)
                                    }
                                }
                            } catch (ex: HttpException) {
                                val resposta = ex.response()
                                val codigoDeErro = ex.code()
                                Log.d("Resposta HTTP", "$resposta")
                                Log.d("Código de erro", "$codigoDeErro")

                            } catch (ex: UnknownHostException) {
                                Log.d("servidor", "O servidor está com algum problema...")
                            }

                        }
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar por ID do usuário")
                    }
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            IconButton(onClick = {
                scope.launch {
                    usuariosMutaveis.clear()
                    usuariosMutaveis.addAll(getUsuarios())
                }
            }) {
                Icon(Icons.Filled.Refresh, contentDescription = "Desfazer filtro por ID de usuário")
            }
        }

        //Carrega sob demanda à medida que o usuário rola na tela
        LazyColumn {
            items(usuariosMutaveis) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                ) {

                    val apresentacao = "Meu nome é ${usuario.nome} e eu amo" +
                        " a disciplina de Programação para Dispositivos Móveis 😍"

                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.sentiment_very_satisfied),
                                contentDescription = "Very satisfied face emoji"
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = usuario.nome,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(text = apresentacao, modifier = Modifier.padding(top = 8.dp))

                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(12.dp))

                        IconButton(onClick = {
                            scope.launch {
                                removerUsuario(usuario.id)
                                usuariosMutaveis.remove(usuario)
                            }
                        }) {
                            Icon(Icons.Filled.Delete, contentDescription = "Remover usuário")
                        }
                    }
                }
            }
        }
    }

}

suspend fun getUsuarios(): List<Usuario> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.listar()
    }
}

suspend fun getUsuarioById(id: String): Response<Usuario> {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.getUsuarioById(id)
    }
}

suspend fun getEndereco(): Endereco {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.getEndereco()
    }
}

suspend fun criarUsuario(nome: String, senha: String): Usuario {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.criarUsuario(Usuario("", nome, senha))
    }
}

suspend fun removerUsuario(id: String) {
    return withContext(Dispatchers.IO) {
        RetrofitClient.usuarioService.removerUsuario(id)
    }
}
