package com.inik.networkimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.inik.networkimage.ui.theme.NetworkImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkImageTheme {
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
//    val painter = rememberImagePainter(
//        data = "https://picsum.photos/2000/3000"
//    )
//    Image(
//        painter = painter,
//        contentDescription = "엔텔로프 캐넌"
//    )
    Column {
        AsyncImage(model = "https://picsum.photos/200/300", contentDescription = "픽숨")
        AsyncImage(model = "https://picsum.photos/200/300", contentDescription = "픽숨")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetworkImageTheme {
        Greeting("Android")
    }
}