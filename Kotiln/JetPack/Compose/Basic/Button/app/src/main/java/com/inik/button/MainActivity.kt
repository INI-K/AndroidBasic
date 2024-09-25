package com.inik.button

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inik.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) {innerPadding->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                Greeting(onButtonClick = {
                    Toast.makeText(this, "출력", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

//@Composable
//fun ButtonExample(onButtonClick: () -> Unit) {
//    Button(onClick = {}){
//        Text(text = "Send")
//    }
//}

@Composable
fun Greeting(onButtonClick: () -> Unit) {
//    Button(onClick = {}){
//        Text(text = "Send")
//    }

    //Toast
//    Button(onClick = onButtonClick) {
//        Text(text = "출력")
//    }

    //Icon
//    Button(onClick = onButtonClick) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Text(text = "출력")
//    }

    //modifier//IconSpacing
//    Button(onClick = onButtonClick) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "출력")
//    }

    //enable
//    Button(onClick = onButtonClick, enabled = false) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "출력")
//    }

    //border
//    Button(onClick = onButtonClick,
//        enabled = true,
//        border = BorderStroke(1.dp, Color.Magenta)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "출력")
//    }

    //Shape
//    Button(onClick = onButtonClick,
//        enabled = true,
//        border = BorderStroke(1.dp, Color.Magenta),
//        shape = CircleShape
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = null
//        )
//        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
//        Text(text = "출력")
//    }
    //contentPadding
    Button(onClick = onButtonClick,
        enabled = true,
        border = BorderStroke(1.dp, Color.Magenta),
        shape = CircleShape,
        contentPadding = PaddingValues(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = "출력")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ButtonTheme {
        Greeting({})
    }
}