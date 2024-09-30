package com.inik.canvasex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.inik.canvasex.ui.theme.CanvasExTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasExTheme {
                CanvasEx()
            }
        }
    }
}

@Composable
fun CanvasEx() {
    Canvas(
        modifier = Modifier.size(20.dp)){
        drawLine(Color.Red, Offset(30f,10f),Offset(50f,40f))
        drawCircle(Color.Yellow,10f,Offset(15f,40f))
        drawRect(Color.Magenta, Offset(30f,30f), Size(10f,10f))

        Icons.Filled.Send

//        moveTo(2.01f, 21.0f)
//        lineTo(23.0f, 12.0f)
//        lineTo(2.01f, 3.0f)
//        lineTo(2.0f, 10.0f)
//        lineToRelative(15.0f, 2.0f)
//        lineToRelative(-15.0f, 2.0f)

        drawLine(Color.Green, Offset(2.01f,21f),Offset(23f,12f))
        drawLine(Color.Green, Offset(23f,12f),Offset(2.01f,3f))
        drawLine(Color.Green, Offset(2.01f,3f),Offset(2.0f,10f))
        drawLine(Color.Green, Offset(2f,10f),Offset(17f,12f))
        drawLine(Color.Green, Offset(17f,12f),Offset(2.0f,14f))
        drawLine(Color.Green, Offset(2.0f,14f),Offset(2.01f,21f))

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasExTheme {
        CanvasEx()
    }
}