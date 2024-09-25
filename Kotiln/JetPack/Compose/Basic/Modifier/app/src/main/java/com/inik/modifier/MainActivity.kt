package com.inik.modifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.inik.modifier.ui.theme.ModifierTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifierTheme {
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
//    Button(onClick = {}) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSpacing))
//        Text("Search")
//    }

    //modifier.fillmaxSize
//    Button(onClick = {},
//        modifier =  Modifier.fillMaxSize()
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text("Search")
//    }

//    Button(onClick = {},
//        modifier =  Modifier.height(100.dp)
//            .width(200.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text("Search")
//    }

//    Button(onClick = {},
//        modifier =  Modifier.size(200.dp,100.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text("Search")
//    }

//    Button(onClick = {},
//        modifier =  Modifier.size(200.dp).background(Color.Red)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text("Search")
//    }
//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta,
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier
//            .height(100.dp)
//            .width(200.dp),
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text("Search")
//    }

//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta,
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier
//            .size(200.dp)
//            .padding(30.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text("Search")
//    }

//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta,
//            contentColor = Color.Cyan
//        ),
//        enabled = false,
//        onClick = {},
//        modifier = Modifier
//            .size(200.dp)
//            .padding(30.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(modifier = modifier.size(ButtonDefaults.IconSize))
//        Text(
//            "Search",
//            modifier = Modifier.clickable {  }
//            )
//    }

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta,
            contentColor = Color.Cyan
        ),
        onClick = {},
        modifier = Modifier
            .size(200.dp)
            .padding(30.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier.background(Color.Blue)
        )
        Spacer(modifier = modifier.size(ButtonDefaults.IconSize).background(Color.Blue))

        Text(
            "Search",
            modifier = Modifier.offset(x = 10.dp).background(Color.Blue)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModifierTheme {
        Greeting("Android")
    }
}