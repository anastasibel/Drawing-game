package com.example.drawinggame.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

data class PathData(
    val path: Path = Path(),
    val color: Color = Color.Black,
    val lineWight: Float = 5f,
)
