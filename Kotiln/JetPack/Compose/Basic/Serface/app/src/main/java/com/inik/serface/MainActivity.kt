package com.inik.serface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inik.serface.ui.theme.SerfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SerfaceTheme {
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
//    Surface(
//        contentColor = MaterialTheme.colorScheme.surface,
//        modifier = Modifier.padding(5.dp)
//    ) {
//        Text(
//            text = "Hello $name",
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//    Surface(
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 5.dp
//    ) {
//        Text(
//            text = "Hello $name",
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//    Surface(
//        border = BorderStroke(width = 2.dp, Color.Magenta),
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 5.dp
//    ) {
//        Text(
//            text = "Hello $name",
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//    Surface(
//        border = BorderStroke(width = 2.dp, Color.Magenta),
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 10.dp,
//        shape = CircleShape
//    ) {
//        Text(
//            text = "Hello $name",
//            modifier = Modifier.padding(8.dp)
//        )
//    }
    Surface(
        border = BorderStroke(width = 2.dp, Color.Magenta),
        modifier = Modifier.padding(5.dp),
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary,
    ) {
        Text(
            text = "Hello $name",
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SerfaceTheme {
        Greeting("Android")
    }
}