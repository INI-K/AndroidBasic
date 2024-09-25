package com.inik.colum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inik.colum.ui.theme.ColumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Column(
//        modifier = Modifier.size(100.dp)
//    ){
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

//    Column(
//        modifier = Modifier.size(100.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

//    Column(
//        modifier = Modifier.size(100.dp),
//        horizontalAlignment = Alignment.End,
//        verticalArrangement = Arrangement.Center
//    ){
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.size(100.dp),
    ) {
        Text(
            text = "첫 번째",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(text = "두 번째")
        Text(
            text = "세 번째",
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColumTheme {
        Greeting("Android")
    }
}