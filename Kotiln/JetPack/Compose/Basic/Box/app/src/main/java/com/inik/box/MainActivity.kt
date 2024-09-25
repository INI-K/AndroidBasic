package com.inik.box

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inik.box.ui.theme.BoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxTheme {
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
//    Box(modifier = Modifier.size(100.dp)) {
//        Text(text = "Hello world", modifier = Modifier.align(Alignment.BottomEnd))
//        Text(text = "JetPack", modifier = Modifier.align(Alignment.CenterEnd))
//        Text(text = "Compose", modifier = Modifier.align(Alignment.TopStart))
//    }

//    Box(modifier = Modifier.size(100.dp)) {
//        Box(modifier =  Modifier.size(70.dp).background(Color.Cyan).align(Alignment.CenterStart))
//        Box(modifier =  Modifier.size(70.dp).background(Color.Yellow).align(Alignment.BottomEnd))
//    }

        Box() {
        Box(modifier =  Modifier.fillMaxSize().background(Color.Cyan).align(Alignment.CenterStart))
        Box(modifier =  Modifier.size(70.dp).background(Color.Yellow).align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoxTheme {
        Greeting("Android")
    }
}