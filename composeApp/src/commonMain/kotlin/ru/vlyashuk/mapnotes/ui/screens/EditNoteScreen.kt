package ru.vlyashuk.mapnotes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import mapnotes.composeapp.generated.resources.Res
import mapnotes.composeapp.generated.resources.coordinates
import mapnotes.composeapp.generated.resources.description
import mapnotes.composeapp.generated.resources.edit_notes
import mapnotes.composeapp.generated.resources.save
import mapnotes.composeapp.generated.resources.title
import org.jetbrains.compose.resources.stringResource
import ru.vlyashuk.mapnotes.data.ItemNotes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    note: ItemNotes,
    onSave: (String, String, String) -> Unit,
    onCancel: () -> Unit
) {

    var title by remember { mutableStateOf(note.title) }
    var coordinates by remember { mutableStateOf(note.coordinates) }
    var description by remember { mutableStateOf(note.description) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.edit_notes)) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(Res.string.title)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = coordinates,
                onValueChange = { coordinates = it },
                label = { Text(stringResource(Res.string.coordinates)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(Res.string.description)) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (title.isNotBlank() && coordinates.isNotBlank()) {
                        onSave(title, coordinates, description)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.save))
            }
        }
    }
}