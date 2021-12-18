package com.nkstudio.movingnumber.myview

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coloredShadow
import com.nkstudio.movingnumber.screenmove.Screen
import com.nkstudio.movingnumber.ui.theme.strongArmy
import com.test.jetback.ui.theme.themecustom.TextSystemColor


@Composable
fun SettingView(navController: NavController) {

    Scaffold() {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                elevation = 20.dp
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Text(
                        text = "설정",
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = strongArmy,
                        fontWeight = FontWeight.Bold,
                        color = TextSystemColor()
                    )
                }
            }
            Column(
                Modifier.fillMaxSize(),
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .coloredShadow(Color.Black)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {
                        navController.navigate(Screen.SettingDetail.route)
                    }) {
                    Text(text = "사진 설정", fontSize = 20.sp)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .coloredShadow(Color.Black)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {
                        navController.navigate(Screen.NumberSettingViewDetail.route)
                    }) {
                    Text(text = "번호 설정", fontSize = 20.sp)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                        .coloredShadow(Color.Black)
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Text(text = "뒤로", fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SettingViewPreview() {
    SettingView(
        navController = rememberNavController()
    )
}
