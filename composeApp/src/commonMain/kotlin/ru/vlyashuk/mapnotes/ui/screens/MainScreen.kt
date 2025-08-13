package ru.vlyashuk.mapnotes.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mapnotes.composeapp.generated.resources.Res
import mapnotes.composeapp.generated.resources.add_notes
import mapnotes.composeapp.generated.resources.cancel
import mapnotes.composeapp.generated.resources.delete
import mapnotes.composeapp.generated.resources.delete_note
import mapnotes.composeapp.generated.resources.delete_note_confirmation
import org.jetbrains.compose.resources.stringResource
import ru.vlyashuk.mapnotes.data.ItemNotes
import ru.vlyashuk.mapnotes.ui.ui_items.NoteCard

@Composable
fun MainScreen(
    notes: List<ItemNotes>,
    onAddClick: () -> Unit,
    navController: NavController,
    onDeleteClick: (Int) -> Unit
) {

    var noteToDeleteId by remember { mutableStateOf<Int?>(null) }

    Scaffold(
        floatingActionButton = {
            CustomFloatingActionButton(
                visible = true,
                onAddClick = {
                    onAddClick()
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (noteToDeleteId != null) {
                AlertDialog(
                    onDismissRequest = { noteToDeleteId = null },
                    title = { Text(stringResource(Res.string.delete_note)) },
                    text = { Text(stringResource(Res.string.delete_note_confirmation)) },
                    confirmButton = {
                        TextButton(onClick = {
                            onDeleteClick(noteToDeleteId!!)
                            noteToDeleteId = null
                        }) {
                            Text(stringResource(Res.string.delete), color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { noteToDeleteId = null }) {
                            Text(stringResource(Res.string.cancel))
                        }
                    }
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(notes, key = { it.id }) { note ->
                    NoteCard(
                        note = note,
                        id = note.id,
                        navController = navController,
                        onDeleteClick = { noteToDeleteId = note.id },
                    )
                }
            }
        }
    }
}

@Composable
fun CustomFloatingActionButton(
    visible: Boolean,
    onAddClick: () -> Unit
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
                    onAddClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(Res.string.add_notes),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}