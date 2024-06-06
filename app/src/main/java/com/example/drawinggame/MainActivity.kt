package com.example.drawinggame

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import com.example.drawinggame.ui.BottomPanel
import com.example.drawinggame.ui.PathData
import com.example.drawinggame.ui.theme.DrawingGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val pathData = remember {
                mutableStateOf(PathData())
            }

            val pathList = remember {
                mutableStateListOf(PathData())
            }

            DrawingGameTheme {
                Column {
                    DrawCanvas(pathData, pathList)
                    BottomPanel(
                        { color ->
                            pathData.value = pathData.value.copy(
                                color = color
                            )
                        },
                        { lineWidth ->
                            pathData.value = pathData.value.copy(
                                lineWidth = lineWidth
                            )
                        },
                        { pattern ->
                            pathData.value = pathData.value.copy(
                                pattern = pattern
                            )
                        },
                        {
                            pathList.removeIf { pathData ->
                                pathList[pathList.size - 1] == pathData
                            }
                        },
                        {
                            pathList.clear()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DrawCanvas(pathData: MutableState<PathData>, pathList: SnapshotStateList<PathData>) {

    var tempPath = Path()

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.70f)
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = {
                        pathList.add(
                            pathData.value.copy(
                                path = tempPath
                            )
                        )
                        tempPath = Path()
                    },
                    onDragEnd = {
                        pathList.add(
                            pathData.value.copy(
                                path = tempPath
                            )
                        )
                    }
                ) { change, dragAmount ->
                    tempPath.moveTo(
                        change.position.x - dragAmount.x,
                        change.position.y - dragAmount.y
                    )
                    tempPath.lineTo(
                        change.position.x,
                        change.position.y
                    )
                    if (pathList.size > 0) {
                        pathList.removeAt(pathList.size - 1)
                    }
                    pathList.add(
                        pathData.value.copy(
                            path = tempPath
                        )
                    )
                }
            }
    ) {
        pathList.forEach { pathData ->
            drawPath(
                path = pathData.path,
                color = pathData.color,
                style = Stroke(
                    pathData.lineWidth,
                    cap = pathData.pattern,
                )
            )
        }
        Log.d("My Log", "Size: ${pathList.size}")
    }
}