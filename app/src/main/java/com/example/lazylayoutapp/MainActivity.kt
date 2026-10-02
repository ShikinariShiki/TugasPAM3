package com.example.lazylayoutapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyLayoutTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DestinationList()
                }
            }
        }
    }
}

data class Destination(
    val title: String,
    val location: String,
    @DrawableRes val imageRes: Int
)

private val destinations = listOf(
    Destination("Suasana Kota", "Jakarta", R.drawable.img_city),
    Destination("Pemandangan Gunung", "Bromo", R.drawable.img_mountain),
    Destination("Pantai Sore", "Bali", R.drawable.img_beach),
    Destination("Akhir Pekan di Kota", "Bandung", R.drawable.img_city),
    Destination("Udara Pegunungan", "Dieng", R.drawable.img_mountain),
    Destination("Tepi Laut", "Lombok", R.drawable.img_beach),
    Destination("Jalan-Jalan Singkat", "Surabaya", R.drawable.img_city),
    Destination("Jalur Pendakian", "Semeru", R.drawable.img_mountain),
    Destination("Liburan Santai", "Malang Selatan", R.drawable.img_beach)
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DestinationList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F9)),
        contentPadding = PaddingValues(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        stickyHeader {
            StudentHeader()
        }
        itemsIndexed(
            items = destinations,
            key = { index, item -> "$index-${item.title}" }
        ) { _, item ->
            DestinationCard(item)
        }
    }
}

@Composable
fun StudentHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F9))
            .padding(horizontal = 18.dp, vertical = 16.dp)
    ) {
        Text(
            text = "PAMTugas3",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF202124)
        )
        Text(
            text = "Nathanael Kevin Irwanto",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF3C4043),
            modifier = Modifier.padding(top = 6.dp)
        )
        Text(
            text = "245150400111059",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun DestinationCard(item: Destination) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF202124),
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = item.location,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(top = 3.dp, bottom = 2.dp)
            )
        }
    }
}

@Composable
fun LazyLayoutTheme(content: @Composable () -> Unit) {
    val googleSans = FontFamily(Font(R.font.google_sans_flex))
    val colors = lightColorScheme(
        primary = Color(0xFF415F91),
        background = Color(0xFFF7F7F9),
        surface = Color.White,
        onBackground = Color(0xFF202124),
        onSurface = Color(0xFF202124)
    )
    val typography = MaterialTheme.typography.run {
        copy(
            displayLarge = displayLarge.withFont(googleSans),
            displayMedium = displayMedium.withFont(googleSans),
            displaySmall = displaySmall.withFont(googleSans),
            headlineLarge = headlineLarge.withFont(googleSans),
            headlineMedium = headlineMedium.withFont(googleSans),
            headlineSmall = headlineSmall.withFont(googleSans),
            titleLarge = titleLarge.withFont(googleSans),
            titleMedium = titleMedium.withFont(googleSans),
            titleSmall = titleSmall.withFont(googleSans),
            bodyLarge = bodyLarge.withFont(googleSans),
            bodyMedium = bodyMedium.withFont(googleSans),
            bodySmall = bodySmall.withFont(googleSans),
            labelLarge = labelLarge.withFont(googleSans),
            labelMedium = labelMedium.withFont(googleSans),
            labelSmall = labelSmall.withFont(googleSans)
        )
    }
    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
}

private fun TextStyle.withFont(fontFamily: FontFamily) = copy(fontFamily = fontFamily)
