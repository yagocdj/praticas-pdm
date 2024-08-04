package br.edu.ifpb.pdm.praticamaterial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.ifpb.pdm.praticamaterial.ui.theme.PraticaMaterialTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PraticaMaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = { Text("Jetpack Compose") },
                            navigationIcon = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        contentDescription = "Navigation menu"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { /* do something */ }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        contentDescription = "Search for something"
                                    )
                                }
                            },
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            actions = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    IconButtonWithText(
                                        onClick = { /* do something */ },
                                        contentDescription = "Go back to home",
                                        icon = Icons.Filled.Home,
                                        buttonText = "Home"
                                    )
                                    IconButtonWithText(
                                        onClick = { /* do something */ },
                                        contentDescription = "Create something",
                                        icon = Icons.Filled.AddCircle,
                                        buttonText = "Create"
                                    )
                                    IconButtonWithText(
                                        onClick = { /* do something */ },
                                        contentDescription = "Go to the app settings",
                                        icon = Icons.Filled.Settings,
                                        buttonText = "Settings"
                                    )
                                }
                            },
                        )
                    },
                    floatingActionButton = {
                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            FloatingActionButton(
                                onClick = { /* do something */ },
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.my_location),
                                    contentDescription = "Go to current location"
                                )
                            }
                            Spacer(modifier = Modifier.size(12.dp))
                            ExtendedFloatingActionButton(
                                onClick = { /* do something */ },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.near_me),
                                        contentDescription = "Navigate to somewhere"
                                    )
                                },
                                text = { Text(text = "Navigate") }
                            )
                        }
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IconButtonWithText(
    onClick: () -> Unit,
    contentDescription: String,
    icon: ImageVector,
    buttonText: String
) {
    TextButton(onClick = onClick) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = contentDescription)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = buttonText)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PraticaMaterialTheme {
        Greeting("Android")
    }
}
