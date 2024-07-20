package com.example.dayoftheweek

import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dayoftheweek.ui.theme.DayOfTheWeekTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DayOfTheWeekTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var dayOfWeekAsDigit by remember { mutableStateOf("") }
    val onlyNumbersPattern = remember { Regex("^\\d*$") }
    var isValid by remember { mutableStateOf(true) }
    val maxSize = 1

    Column {
        Text(
            text = stringResource(R.string.user_input_prompt),
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        )
        OutlinedTextField(
            value = dayOfWeekAsDigit,
            onValueChange = {
                if (it.matches(onlyNumbersPattern) && it.length <= maxSize) {
                    dayOfWeekAsDigit = it
                    isValid = if (it.isNotEmpty()) it.toInt() in 1..7 else true
                }
            },
            label = { Text(stringResource((R.string.input_label))) },
            isError = !isValid,
            modifier = Modifier.padding(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (!isValid) {
            Text(text = stringResource(R.string.invalid_range_input),
                modifier = Modifier.padding(start = 20.dp),
                color = Color(0xFFFFCDD2)
            )
        }
        Text(
            text = when (dayOfWeekAsDigit) {
                "1" -> stringArrayResource(R.array.days_of_week)[0]
                "2" -> stringArrayResource(R.array.days_of_week)[1]
                "3" -> stringArrayResource(R.array.days_of_week)[2]
                "4" -> stringArrayResource(R.array.days_of_week)[3]
                "5" -> stringArrayResource(R.array.days_of_week)[4]
                "6" -> stringArrayResource(R.array.days_of_week)[5]
                "7" -> stringArrayResource(R.array.days_of_week)[6]
                else -> ""
            },
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DayOfTheWeekTheme {
        Greeting()
    }
}