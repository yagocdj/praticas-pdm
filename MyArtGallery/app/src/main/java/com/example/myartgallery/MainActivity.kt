package com.example.myartgallery

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
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
    val paddingInDp = 18.dp

    // Each image has its index (starts at 1)
    var currentImageIndex by remember { mutableIntStateOf(1) }
    val numberOfImages = 3

    var artworkTitle by remember { mutableStateOf("") }
    var artworkArtist by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingInDp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageArea(
            currentImageIndex = currentImageIndex
        )
        Spacer(modifier = Modifier.size(18.dp))
        ArtworkDescription()
        DisplayController(
            onClickNext = {
                currentImageIndex =
                    if (currentImageIndex == numberOfImages) 1 else currentImageIndex + 1
            },
            onClickPrevious = {
                currentImageIndex =
                    if (currentImageIndex == 1) numberOfImages else currentImageIndex - 1
            }
        )
    }
}

@Composable
fun ImageArea(
    modifier: Modifier = Modifier,
    currentImageIndex: Int
) {
    Surface(
        modifier = modifier
            .padding(vertical = 18.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        shadowElevation = 10.dp,
    ) {
        Image(
            modifier = Modifier.padding(12.dp),
            painter = painterResource(id = chooseCurrentImage(currentImageIndex)),
            contentDescription = "Black cat inside of a bed cover."
        )
    }
}

@Composable
fun ArtworkDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEBEBEB))
            .padding(vertical = 12.dp, horizontal = 12.dp)
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
fun DisplayController(
    modifier: Modifier = Modifier,
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
) {
    val buttonWidth = 110.dp
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 18.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            modifier = Modifier.width(buttonWidth),
            onClick = onClickPrevious
        ) {
            Text("Previous")
        }
        Button(
            modifier = Modifier.width(buttonWidth),
            onClick = onClickNext
        ) {
            Text("Next")
        }
    }
}

/**
 * Selects the image (drawable) to be displayed based on it's index (an integer ID for each drawable)
 */
private fun chooseCurrentImage(index: Int): Int {
    return when (index) {
        1 -> R.drawable.bruno_thethe
        2 -> R.drawable.eduardo_gorghetto
        3 -> R.drawable.dewang_gupta
        4 -> R.drawable.miguel_romero_gonzalez
        else -> R.drawable.tania_malrechauffe
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyArtGalleryTheme {
        ArtGallery()
    }
}
