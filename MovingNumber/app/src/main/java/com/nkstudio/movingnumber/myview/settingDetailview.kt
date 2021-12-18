package com.nkstudio.movingnumber.myview

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coloredShadow
import com.nkstudio.movingnumber.MainActivity.Companion.photoUri
import com.nkstudio.movingnumber.savesystem.SaveSystem
import com.nkstudio.movingnumber.ui.theme.strongArmy
import com.skydoves.landscapist.glide.GlideImage
import com.test.jetback.ui.theme.themecustom.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun SettingDetailView(navController: NavController) {

    var currentIndex by remember {
        mutableStateOf(0)
    }

    val imageUri = remember {
        mutableStateMapOf(
            0 to photoUri[0],
            1 to photoUri[1],
            2 to photoUri[2]
        )
    }

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            imageUri[currentIndex] = uri.toString()
            photoUri[currentIndex] = uri.toString()
            val saveSystem = SaveSystem(context)

            MainScope().launch {
                saveSystem.save("photo[${currentIndex}]", uri.toString())

            }
        }

    Column(Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                //.background(Color.Red)
                .fillMaxSize(),
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "사진 선택하기",
                    fontSize = 40.sp,
                    fontFamily = strongArmy,
                    fontWeight = FontWeight.Bold,
                    color = TextSystemColor(), modifier = Modifier.padding(top = 30.dp)
                )
                //region 미리보기 사진
                Box(
                    Modifier
                        .background(ButtonSystemColor())
                        .size(225.dp, 400.dp)
                ) {
                    if (imageUri[currentIndex] != null)
                        GlideImage(
                            modifier = Modifier.coloredShadow(
                                Color.Black,
                                0.3f,
                                offsetY = 10.dp
                            ),
                            imageModel = imageUri[currentIndex],
                            contentScale = ContentScale.FillBounds
                        )
                }
                //endregion
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                modifier = Modifier.coloredShadow(
                    ElevationSystemColor(),
                    alpha = 0.5f,
                    shadowRadius = 10.dp,
                    borderRadius = 40.dp,
                    offsetY = 3.dp
                ),
                border = BorderSystemColor(),
                onClick = {
                    currentIndex = 0
                    imageUri[currentIndex] = photoUri[0]
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonSystemColor()
                ),
            ) {
                Text(text = "1", fontSize = 30.sp, color = TextSystemColor())
            }

            Button(
                modifier = Modifier.coloredShadow(
                    ElevationSystemColor(),
                    alpha = 0.5f,
                    shadowRadius = 10.dp,
                    borderRadius = 40.dp,
                    offsetY = 3.dp
                ),
                border = BorderSystemColor(),
                onClick = {
                    currentIndex = 1
                    imageUri[currentIndex] = photoUri[1]
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonSystemColor()
                ),
            ) {
                Text(text = "2", fontSize = 30.sp, color = TextSystemColor())
            }

            Button(
                modifier = Modifier.coloredShadow(
                    ElevationSystemColor(),
                    alpha = 0.5f,
                    shadowRadius = 10.dp,
                    borderRadius = 40.dp,
                    offsetY = 3.dp
                ),
                border = BorderSystemColor(),
                onClick = {
                    currentIndex = 2
                    imageUri[currentIndex] = photoUri[2]
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonSystemColor()
                ),
            ) {
                Text(text = "3", fontSize = 30.sp, color = TextSystemColor())
            }
        }
        Box(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                //region CustomButton
                Button(modifier = Modifier
                    .size(250.dp, 60.dp)
                    .coloredShadow(
                        ElevationSystemColor(),
                        alpha = 0.5f,
                        shadowRadius = 10.dp,
                        borderRadius = 40.dp,
                        offsetY = 3.dp
                    ),
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
                //endregion
                //region CustomButton
                Button(modifier = Modifier
                    .size(250.dp, 60.dp)
                    .coloredShadow(
                        ElevationSystemColor(),
                        alpha = 0.5f,
                        shadowRadius = 10.dp,
                        borderRadius = 40.dp,
                        offsetY = 3.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ButtonSystemColor()
                    ),
                    shape = RoundedCornerShape(40.dp),
                    border = BorderSystemColor(),
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Text(
                        text = "뒤로",
                        fontSize = 30.sp,
                        fontFamily = strongArmy,
                        fontWeight = FontWeight.Normal,
                        color = ButtonTextSystemColor()
                    )
                }
                //endregion
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun SettingDetailViewPreview() {
    SettingDetailView(
        navController = rememberNavController()
    )
}
