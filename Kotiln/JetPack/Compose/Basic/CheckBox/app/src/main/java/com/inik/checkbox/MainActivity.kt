package com.inik.checkbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inik.checkbox.ui.theme.CheckBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckBoxTheme {
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
//        Checkbox(
//            checked = false,
//            onCheckedChange = {}
//        )
//        var checked = false
//        Checkbox(
//            checked = checked,
//            onCheckedChange = {
//                checked = !checked
//            }
//        )
//        Text(text = "Android Developer")

//        var checked = remember {mutableStateOf(false)}
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = {
//                checked.value = !checked.value
//            }
//        )
//        Text(text = "Android Developer")

//        var checked by remember {mutableStateOf(false)}
//        Checkbox(
//            checked = checked,
//            onCheckedChange = {
//                checked = !checked
//            }
//        )
//        Text(text = "Android Developer")

        val (getter, setter) = remember {mutableStateOf(false)}
        Checkbox(
            checked = getter,
            onCheckedChange = setter
        )
        Text(
            text = "Android Developer",
            modifier = Modifier.clickable{
                setter(!getter)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CheckBoxTheme {
        Greeting("Android")
    }
}