package com.inik.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inik.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
//                Text("hello")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    //Color
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color.Red
//    )
    //HexColor
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color(0xffff9944)
//    )
    //TextSize
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color(0xffff9944),
//        fontSize = 30.sp
//    )
    //TextStyle
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color(0xffff9944),
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold
//    )
    //TextFont
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color(0xffff9944),
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive
//    )
    //letterSpacing
//    Text(
//        text = "Hello $name!",
//        modifier = modifier,
//        color = Color(0xffff9944),
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        letterSpacing = 2.sp
//    )

    //Text Decoraiton
//    Text(
//        text = "Hello $name!\nHello $name!",
//        modifier = modifier,
//        color = Color(0xffff9944),
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        letterSpacing = 2.sp,
//        maxLines = 2,
//        textDecoration = TextDecoration.Underline
//    )
    //Modifier
    Text(
        text = "Hello $name!\nHello $name!",
        modifier = Modifier.size(400.dp),
        color = Color(0xffff9944),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        letterSpacing = 2.sp,
        maxLines = 2,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloComposeTheme {
        Greeting("Android")
    }
}