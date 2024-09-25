package com.inik.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.inik.profilecard.ui.theme.ProfileCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        cardData,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUri = "https://picsum.photos/200/300",
            imageDescription = "픽숨",
            author = "INI-K",
            description = "픽숨 Test 가나다라 마바사 가나다라 마바사 가나다라 마바사 가나다라 마바사 "
        )
    }
}

@Composable
fun Greeting(cardData: CardData, modifier: Modifier = Modifier) {
    val placeHolderColor = Color(0x33000000)
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
//            AsyncImage(
//                model = cardData.imageUri,
//                contentDescription = cardData.imageDescription,
//                modifier = Modifier.size(32.dp)
//            )
//            Spacer(
//                modifier = Modifier.size(8.dp),
//            )
//            Column {
//                Text(
//                    text = cardData.author,
//                )
//                Spacer(
//                    modifier = Modifier.size(4.dp)
//                )
//                Text(
//                    text = cardData.description,
//                )
//            }
            AsyncImage(
                model = cardData.imageUri,
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(placeHolderColor),
                contentDescription = cardData.imageDescription,
                modifier = Modifier.size(150.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Spacer(
                modifier = Modifier.size(8.dp),
            )
            Column {
                Text(
                    text = cardData.author,
                )
                Spacer(
                    modifier = Modifier.size(4.dp)
                )
                Text(
                    text = cardData.description,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProfileCardTheme {
        Greeting(MainActivity.cardData)
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)