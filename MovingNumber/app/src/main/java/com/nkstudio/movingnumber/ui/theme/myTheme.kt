package com.test.jetback.ui.theme.themecustom

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nkstudio.movingnumber.ui.theme.Dark

@Composable
fun TextSystemColor(): Color {
    val isLight = MaterialTheme.colors.isLight

    return if (isLight)
        Color.Black
    else
        Color.White
}

@Composable
fun ButtonTextSystemColor(): Color {
    val isLight = MaterialTheme.colors.isLight

    return if (isLight)
        Color.Black
    else
        Color(0xFFbb86fc)
}

@Composable
fun BorderSystemColor(): BorderStroke {
    val isLight = MaterialTheme.colors.isLight

    return if (isLight)
        BorderStroke(2.dp, Color(0xFF23BAFF))
    else
        BorderStroke(2.dp, Color(0xFFBB86FC))
}

@Composable
fun ButtonSystemColor(): Color {
    val isLight = MaterialTheme.colors.isLight

    return if (isLight)
        Color.White
    else
        Dark
}

@Composable
fun ElevationSystemColor(): Color {
    val isLight = MaterialTheme.colors.isLight

    return if (isLight)
        Color(0xFF23BAFF)
    else
        Color(0xFF000000)
}