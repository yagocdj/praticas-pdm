package br.edu.ifpb.pdm.scaffoldexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifpb.pdm.scaffoldexample.ui.theme.ScaffoldExampleTheme
import br.edu.ifpb.pdm.scaffoldexample.ui.theme.WhatsAppGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldExampleTheme {
                MyApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var daysCount by remember { mutableIntStateOf(0) }
    val xlargeFontSize = 36.sp

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    // Using WhatsApp green for the container's color
                    containerColor = colorResource(id = R.color.whatsapp_green),
                    titleContentColor = colorResource(id = R.color.white),
                ),
                // App's main heading :D
                title = {
                    Text(text = "WhatsApp 2", fontWeight = FontWeight.Bold)
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(id = R.color.whatsapp_green),
                contentColor = colorResource(id = R.color.white),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "See you soon ;)",
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { daysCount++ },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                text = { Text(text = "Days count") },
                containerColor = colorResource(id = R.color.whatsapp_green),
                contentColor = colorResource(id = R.color.white)
            )
        }
    ) { innerPadding ->
        // Main content
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = ParagraphStyle(
                            lineHeight = 36.sp,
                            textAlign = TextAlign.Center
                        )
                    ) {
                        withStyle(
                            style = SpanStyle(
                                color = WhatsAppGreen,
                                fontWeight = FontWeight.Bold,
                                fontSize = xlargeFontSize
                            ),
                        ) {
                            append("WhatsApp 2 ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontSize = xlargeFontSize,
                            )
                        ) {
                            append("will be launched in $daysCount days.")
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ScaffoldExampleTheme {
        MyApp()
    }
}
