package com.test.jetback

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coloredShadow
import com.skydoves.landscapist.glide.GlideImage
import com.test.jetback.MainActivity.Companion.photoUri
import com.test.jetback.ui.theme.strongArmy
import com.test.jetback.ui.theme.themecustom.BorderSystemColor
import com.test.jetback.ui.theme.themecustom.ButtonSystemColor
import com.test.jetback.ui.theme.themecustom.ButtonTextSystemColor
import com.test.jetback.ui.theme.themecustom.ElevationSystemColor
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Composable
fun SettingView()
{
    var imageUri = remember {
        mutableStateMapOf(
            0 to photoUri[0],
            1 to photoUri[1],
            2 to photoUri[2]
        )
    }

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            imageUri[0] = uri.toString()
            val saveSystem = SaveSystem(context)

            GlobalScope.launch {
                saveSystem.save("photo", uri.toString())
            }
        }

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                //.background(Color.Red)
                .fillMaxSize()
        ) {
            Box(
                Modifier
                    .coloredShadow(Color.Black, 0.3f, offsetY = 10.dp)
                    .background(Color.White)
                    .size(270.dp, 480.dp)
                    .align(Alignment.Center)
            ) {
                if (imageUri[0] != null)
                    GlideImage(imageModel = imageUri[0], contentScale = ContentScale.Fit)
            }
        }

        Box(
            modifier = Modifier
                .weight(0.3f)
                //.background(Color.Yellow)
                .fillMaxSize()
        ) {
            Button(modifier = Modifier
                .size(250.dp, 80.dp)
                .coloredShadow(
                    ElevationSystemColor(),
                    alpha = 0.5f,
                    shadowRadius = 10.dp,
                    borderRadius = 40.dp,
                    offsetY = 3.dp
                )
                .align(Alignment.Center),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonSystemColor()
                ),
                shape = RoundedCornerShape(40.dp),
                border = BorderSystemColor(),
                onClick = {
                    launcher.launch("image/*")
                }) {
                Text(
                    text = "갤러리 열기",
                    fontSize = 30.sp,
                    fontFamily = strongArmy,
                    fontWeight = FontWeight.Normal,
                    color = ButtonTextSystemColor()
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun SettingViewPreview()
{
    SettingView()
}
