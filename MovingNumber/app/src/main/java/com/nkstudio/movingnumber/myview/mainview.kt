package com.nkstudio.movingnumber.myview

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coloredShadow
import com.nkstudio.movingnumber.MainActivity.Companion.finishShow
import com.nkstudio.movingnumber.MainActivity.Companion.startCall
import com.nkstudio.movingnumber.R
import com.nkstudio.movingnumber.myservice.GoHomeService
import com.nkstudio.movingnumber.screenmove.Screen
import com.nkstudio.movingnumber.ui.theme.strongArmy
import com.test.jetback.ui.theme.themecustom.*

@Composable
fun MainView(navController: NavController, context: Context) {
    Scaffold() {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = strongArmy,
            textAlign = TextAlign.Center,
            text = stringResource(R.string.app_name),
            style = TextStyle(
                color = TextSystemColor(), fontSize = 70.sp
            )
        )
        Column(
            Modifier
                .padding(bottom = 60.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            Button(modifier = Modifier
                .padding(40.dp)
                .size(250.dp, 80.dp)
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
                    startCall = false
                    finishShow = false
                    navController.navigate(Screen.Call.route)
                    val intent = Intent(context, GoHomeService::class.java)
                    context.startService(intent)
                }) {
                Text(
                    text = "시작",
                    fontSize = 30.sp,
                    fontFamily = strongArmy,
                    fontWeight = FontWeight.Normal,
                    color = ButtonTextSystemColor()
                )
            }

            Button(modifier = Modifier
                .padding(40.dp)
                .size(250.dp, 80.dp)
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
                    startCall= false
                    finishShow = false
                    navController.navigate(route = Screen.Setting.route)
                }) {
                Text(
                    text = "설정",
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
fun MainViewPreview() {
    MainView(
        navController = rememberNavController(),
        context = LocalContext.current
    )
}