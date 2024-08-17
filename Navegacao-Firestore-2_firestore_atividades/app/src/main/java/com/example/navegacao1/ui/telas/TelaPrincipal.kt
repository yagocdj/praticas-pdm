package com.example.navegacao1.ui.telas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.navegacao1.R
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        Text(text = "Tela Principal")
        val usuarios = remember { mutableStateListOf<Usuario>() }

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                usuarioDAO.buscar(callback = { usuariosRetornados ->
                    usuarios.clear()
                    usuarios.addAll(usuariosRetornados)
                })
            }
        }) {
            Text("Carregar")
        }
        Button(onClick = { onLogoffClick() }) {
            Text("Sair")
        }

        //Carrega sob demanda à medida que o usuário rola na tela
        LazyColumn() {
            items(usuarios) { usuario ->
                Spacer(modifier = Modifier.height(10.dp))
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
                    }
                }
            }
        }
    }

}
