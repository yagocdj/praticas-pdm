package com.example.navegacao1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navegacao1.ui.telas.TelaCadastro
import com.example.navegacao1.ui.telas.TelaLogin
import com.example.navegacao1.ui.telas.TelaPrincipal
import com.example.navegacao1.ui.theme.Navegacao1Theme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        setContent {
            Navegacao1Theme {
                var tituloDaTela by remember { mutableStateOf("Login") }

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(tituloDaTela) },
                            Modifier.background(MaterialTheme.colorScheme.secondary)
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val navController = rememberNavController()

                    val navigateToLogin: () -> Unit = {
                        tituloDaTela = "Login"
                        navController.navigate("login")
                    }
                    val navigateToMain: () -> Unit = {
                        tituloDaTela = "Principal"
                        navController.navigate("principal")
                    }
                    val navigateToRegister: () -> Unit = {
                        tituloDaTela = "Cadastro"
                        navController.navigate("cadastro")
                    }
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            TelaLogin(
                                modifier = Modifier.padding(innerPadding),
                                onSigninClick = navigateToMain,
                                onRegisterClick = navigateToRegister
                            )
                        }
                        composable("principal") {
                            TelaPrincipal(
                                modifier = Modifier.padding(innerPadding),
                                onLogoffClick = navigateToLogin
                            )
                        }
                        composable("cadastro") {
                            TelaCadastro(
                                modifier = Modifier.padding(innerPadding),
                                onRegisterClick = navigateToLogin,
                                onGoBackClick = navigateToLogin)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    TelaLogin()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Navegacao1Theme {
//        TelaLogin()
    }
}
