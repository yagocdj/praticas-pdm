package com.example.myartgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myartgallery.ui.theme.MyArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyArtGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtGallery(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    Column (Modifier.fillMaxSize()) {
        ImageArea()
        ArtworkDescription()
        DisplayController()
    }
}

@Composable
fun ImageArea(modifier: Modifier = Modifier) {
    Surface (
        modifier = modifier
            .padding(18.dp),
        shadowElevation = 40.dp,

    ) {
        Image(
            modifier = Modifier.padding(12.dp),
            painter = painterResource(R.drawable.andreea_popa_6st6s6i9igm_unsplash),
            contentDescription = "Black cat inside of a bed cover."
        )
    }
}

@Composable
fun ArtworkDescription(modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Artwork Title",
            modifier = modifier,
            fontWeight = FontWeight.Thin,
            fontSize = 24.sp
        )
        Text(
            text = "Artwork Artist (year)",
            modifier = modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun DisplayController(modifier: Modifier = Modifier) {
    val buttonWidth = 110.dp
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.width(buttonWidth),
            shape = RectangleShape,
            onClick = { /*TODO*/ }
        ) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            modifier = Modifier.width(buttonWidth),
            shape = RectangleShape,
            onClick = { /*TODO*/ }
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyArtGalleryTheme {
        ArtGallery()
    }
}
