package com.example.drawinggame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.drawinggame.ui.theme.MyGreen
import com.example.drawinggame.ui.theme.MyLightBlue
import com.example.drawinggame.ui.theme.MyOrange
import com.example.drawinggame.ui.theme.MyPurple
import com.example.drawinggame.ui.theme.MyYellow

@Composable
fun BottomPanel(onClick: (Color) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorList() { color ->
            onClick(color)
        }
    }
}

@Composable
fun ColorList(onClick: (Color) -> Unit) {

    val colors = listOf(
        Color.Black,
        Color.White,
        Color.Red,
        MyOrange,
        MyYellow,
        MyGreen,
        MyLightBlue,
        Color.Blue,
        MyPurple,
        )

    LazyRow(
        modifier = Modifier.padding(5.dp)
    )
    {
        items(colors) { color ->
            Box(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable {
                        onClick(color)
                    }
                    .size(30.dp)
                    .background(color, CircleShape)
            )
        }
    }
}