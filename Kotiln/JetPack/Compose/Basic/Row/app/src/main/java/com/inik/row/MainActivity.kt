package com.inik.row

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inik.row.ui.theme.RowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RowTheme {
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
//    Row(modifier = Modifier.height(40.dp)){
//        Text(text = "첫 번째!")
//        Text(text = "두 번째!")
//        Text(text = "세 번째!")
//    }


//    Row(modifier = Modifier.height(40.dp)){
//        Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
//        Text(text = "두 번째!",modifier = Modifier.align(Alignment.CenterVertically))
//        Text(text = "세 번째!",modifier = Modifier.align(Alignment.Bottom))
//    }

//    Row(modifier = Modifier.height(40.dp),
//        verticalAlignment = Alignment.Bottom
//    ){
//        Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
//        Text(text = "두 번째!")
//        Text(text = "세 번째!")
//    }

//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        modifier = Modifier
//            .height(40.dp)
//            .width(200.dp),
//        verticalAlignment = Alignment.Bottom
//    ) {
//        Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
//        Text(text = "두 번째!")
//        Text(text = "세 번째!")
//    }

//    Row(
//        modifier = Modifier
//            .height(40.dp)
//            .width(200.dp),
//        verticalAlignment = Alignment.Bottom
//    ) {
//        Text(
//            text = "첫 번째!",
//            modifier = Modifier.align(Alignment.Top)
//                .weight(3f)
//        )
//        Text(
//            text = "두 번째!",
//            modifier = Modifier.weight(1f)
//        )
//        Text(
//            text = "세 번째!",
//            modifier = Modifier.weight(3f)
//        )
//    }

//    Row(
//        modifier = Modifier
//            .height(40.dp)
//            .width(200.dp),
//        verticalAlignment = Alignment.Bottom
//    ) {
//        Text(
//            text = "첫 번째!",
//            modifier = Modifier.align(Alignment.Top)
//                .weight(3f)
//        )
//        Icon(
//            imageVector = Icons.Filled.Add,
//            contentDescription = "추가"
//        )
//        Text(
//            text = "세 번째!",
//            modifier = Modifier.weight(3f)
//        )
//    }


    Row(
        modifier = Modifier
            .height(40.dp)
            .width(200.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "첫 번째!",
            textAlign = TextAlign.End,
            modifier = Modifier.align(Alignment.Top)
                .weight(3f)
                .background(Color.Magenta)
        )
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "추가",
            modifier = Modifier.weight(1f).background(Color.Cyan)
        )
        Text(
            text = "세 번째!",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(3f).background(Color.Blue)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RowTheme {
        Greeting("Android")
    }
}