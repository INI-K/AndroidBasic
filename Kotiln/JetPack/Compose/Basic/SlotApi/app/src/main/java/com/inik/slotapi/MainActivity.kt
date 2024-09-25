package com.inik.slotapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inik.slotapi.ui.theme.SlotApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlotApiTheme {
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

//@Composable
//fun CheckBoxWithText(checked: MutableState<Boolean>,text:String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it }
//        )
//        Text(
//            text = text,
//            modifier = Modifier.clickable { checked.value = !checked.value }
//        )
//    }
//}
//
//@Composable
//fun CheckBoxWithSlot(checked: MutableState<Boolean>, content: @Composable () -> Unit) {
//    Row(verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it }
//        )
//        content()
//    }
//}


//@Composable
//fun CheckBoxWithSlot(checked: MutableState<Boolean>, content: @Composable RowScope.() -> Unit) {
//    Row(verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it }
//        )
//        content()
//    }
//}
@Composable
fun CheckBoxWithSlot(
    checked: Boolean,
    onCheckedChanged: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged()
        }) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChanged() }
        )
        content()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }
    Column {
//        CheckBoxWithSlot(checked1) {
//            Text("텍스트1")
//        }
//        CheckBoxWithSlot(checked2) {
//            Text("텍스트2")
//        }

        CheckBoxWithSlot(checked1.value,
            onCheckedChanged = {
                checked1.value = !checked1.value
            }) {
            Text("텍스트1")
        }
        CheckBoxWithSlot(checked2.value,
            onCheckedChanged = {
                checked2.value = !checked2.value
            }) {
            Text("텍스트3")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlotApiTheme {
        Greeting("Android")
    }
}