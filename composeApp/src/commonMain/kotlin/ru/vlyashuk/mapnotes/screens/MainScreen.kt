package ru.vlyashuk.mapnotes.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.vlyashuk.mapnotes.data.ItemNotes
import ru.vlyashuk.mapnotes.ui_items.NoteCard

@Composable
fun MainScreen() {

    val note = ItemNotes(
        id = 1,
        title = "Дом",
        coordinates = "55.7558, 37.6173",
        description = "Это моя точка на карте, где находится дом"
    )

    Scaffold(
        floatingActionButton = {
            CustomFloatingActionButton(
                visible = true,
                onClick = { /*TODO*/ },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    NoteCard(note = note)
                }
            }
        }
    }
}

@Composable
fun CustomFloatingActionButton(
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .graphicsLayer {
                    shadowElevation = 6.dp.toPx()
                    shape = RoundedCornerShape(100)
                    clip = true
                }
                .background(MaterialTheme.colorScheme.primary)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Добавить точку",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}