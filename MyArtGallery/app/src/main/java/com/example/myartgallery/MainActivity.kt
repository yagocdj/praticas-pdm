package com.example.myartgallery

import android.os.Bundle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
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
    var currentArtIndex by remember { mutableIntStateOf(1) }
    val NUMBER_OF_IMAGES = 4

    var artworkTitle by remember { mutableStateOf("") }
    var artworkArtist by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingInDp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageArea(
            currentArtIndex = currentArtIndex
        )
        Spacer(modifier = Modifier.size(18.dp))
        ArtworkDescription(
            currentArtIndex = currentArtIndex
        )
        DisplayController(
            onClickNext = {
                currentArtIndex =
                    if (currentArtIndex == NUMBER_OF_IMAGES) 1 else currentArtIndex + 1
            },
            onClickPrevious = {
                currentArtIndex =
                    if (currentArtIndex == 1) NUMBER_OF_IMAGES else currentArtIndex - 1
            }
        )
    }
}

@Composable
fun ImageArea(
    modifier: Modifier = Modifier,
    currentArtIndex: Int
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
            painter = painterResource(id = chooseCurrentImage(currentArtIndex)),
            contentDescription = stringArrayResource(id = chooseCurrentArtInfo(currentArtIndex))[3]
        )
    }
}

@Composable
fun ArtworkDescription(
    modifier: Modifier = Modifier,
    currentArtIndex: Int
) {
    val artInfo = stringArrayResource(id = chooseCurrentArtInfo(currentArtIndex))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFEBEBEB))
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Text(
            /*
             The "[0]" is used to access the first item of the string-array (in this case, the art's
             title
            */
            text = artInfo[0],
            modifier = modifier,
            fontWeight = FontWeight.Thin,
            fontSize = 24.sp
        )
        Text(
            text = "${artInfo[1]} (${artInfo[2]})",
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
 * @param index index of current image
 * @return the drawable as an integer value
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

/**
 * Selects the information of a piece of art given it's index (an integer ID for each artwork)
 * @param index index of current artwork
 * @return the description of that artwork as an array (it can be accessed using
 * <code>stringArrayResource</code> composable)
 */
private fun chooseCurrentArtInfo(index: Int): Int {
    return when (index) {
        1 -> R.array.art_bruno_thethe
        2 -> R.array.art_eduardo_gorghetto
        3 -> R.array.art_dewang_gupta
        4 -> R.array.art_miguel_romero_gonzalez
        else -> R.array.art_tania_malrechauffe
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyArtGalleryTheme {
        ArtGallery()
    }
}
