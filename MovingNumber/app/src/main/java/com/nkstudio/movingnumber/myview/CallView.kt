package com.nkstudio.movingnumber.myview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.nkstudio.movingnumber.MainActivity
import com.nkstudio.movingnumber.MainActivity.Companion.photoUri
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay

@Composable
fun CallView() {

    var changeScreen by remember {
        mutableStateOf(0)
    }

    var change by remember {
        mutableStateOf(false)
    }

    var myShow by remember {
        mutableStateOf(false)
    }

    val interactionSource = remember { MutableInteractionSource() }

    Box(Modifier.fillMaxSize()) {


        GlideImage(
            imageModel = photoUri[0],
            contentScale = ContentScale.Fit,
            modifier = Modifier.clickable(interactionSource, indication = null) {
                change = true
            }
        )
        val startcall by rememberUpdatedState(newValue = MainActivity.startCall)

        GlideImage(
            imageModel = photoUri[1],
            contentScale = ContentScale.Fit,
            alpha = if (!startcall && changeScreen > 0) 1f else 0f,
        )
        GlideImage(
            imageModel = photoUri[2],
            contentScale = ContentScale.Fit,
            alpha = if (!startcall && changeScreen == 1) 1f else 0f,
        )
    }

    if(change){
        LaunchedEffect(true) {
            while (change) {
                changeScreen = if (changeScreen == 1) 2 else 1
                delay(500)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CallViewPreview() {
    CallView()
}