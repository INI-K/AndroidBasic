package com.inik.constraintlayout

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.ConstraintSet
import com.inik.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintLayoutTheme {
                ConstraintLayoutEx()
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Composable
fun ConstraintLayoutEx() {
//    val constraintSet = ConstraintSet {
//        val redBox = createRefFor("redBox")
//        val magentaBox = createRefFor("magentaBox")
//        val greenBox = createRefFor("greenBox")
//        val yellowBox = createRefFor("yellowBox")
//
//        constrain(redBox) {
//            bottom.linkTo(parent.bottom, margin = 10.dp)
//            end.linkTo(parent.end, margin = 30.dp)
//        }
//
//        constrain(magentaBox) {
//            start.linkTo(parent.start, 10.dp)
//            end.linkTo(parent.end, 30.dp)
//        }
//
//        constrain(greenBox) {
//            centerTo(parent)
//        }
//
//        constrain(yellowBox) {
//            start.linkTo(magentaBox.end)
//            top.linkTo(magentaBox.bottom)
//        }
//    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (redBox, magentaBox, yellowBox,text) = createRefs()
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    start.linkTo(parent.start, margin = 48.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(parent.top, margin = 32.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(parent.top, margin = 680.dp)
                }
        )
//        Box(
//            modifier = Modifier
//                .size(40.dp)
//                .background(Color.Green)
//                .constrainAs(greenBox){}
//        )

        val barrier = createEndBarrier(redBox, yellowBox, magentaBox)

        Text(
            text = "테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트",
            modifier = Modifier.constrainAs(text){
                start.linkTo(barrier)
                bottom.linkTo(magentaBox.bottom)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayoutTheme {
        ConstraintLayoutEx()
    }
}