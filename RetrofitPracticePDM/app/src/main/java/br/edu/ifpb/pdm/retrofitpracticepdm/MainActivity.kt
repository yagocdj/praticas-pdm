package br.edu.ifpb.pdm.retrofitpracticepdm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifpb.pdm.retrofitpracticepdm.model.Endereco
import br.edu.ifpb.pdm.retrofitpracticepdm.ui.theme.RetrofitPracticePDMTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import br.edu.ifpb.pdm.retrofitpracticepdm.model.RetrofitClient
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitPracticePDMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AddressSearchAndListScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AddressSearchAndListScreen(name: String, modifier: Modifier = Modifier) {

    val scope = rememberCoroutineScope()

    var cep by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf<Endereco>(Endereco()) }

    Column {
        OutlinedTextField(value = cep, onValueChange = {
            cep = it
        })

        Button(onClick = {
            scope.launch {

                endereco = getEndereco(cep)
            }
        }) { Text(text = "Buscar") }
        
        Card {
            Text(text = "$endereco")
        }

    }
}

suspend fun getEndereco(cep: String): Endereco {
    return withContext(Dispatchers.IO) {
        RetrofitClient.enderecoService.getEndereco(cep)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RetrofitPracticePDMTheme {
        AddressSearchAndListScreen("Android")
    }
}