package ru.vlyashuk.mapnotes.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mapnotes.composeapp.generated.resources.Res
import mapnotes.composeapp.generated.resources.ic_dark_mode
import mapnotes.composeapp.generated.resources.ic_light_mode
import mapnotes.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ru.vlyashuk.mapnotes.theme.LocalThemeIsDark

@Composable
fun ProfileScreen() {

    var isDark by LocalThemeIsDark.current
    val icon = remember(isDark) {
        if (isDark) Res.drawable.ic_light_mode
        else Res.drawable.ic_dark_mode
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        ElevatedButton(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                .widthIn(min = 200.dp),
            onClick = { isDark = !isDark },
            content = {
                Icon(vectorResource(icon), contentDescription = null)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(Res.string.theme))
            }
        )
    }
}