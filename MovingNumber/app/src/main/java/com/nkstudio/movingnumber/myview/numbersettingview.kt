package com.nkstudio.movingnumber.myview

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coloredShadow
import com.nkstudio.movingnumber.MainActivity.Companion.phoneNumber
import com.nkstudio.movingnumber.savesystem.SaveSystem
import com.nkstudio.movingnumber.ui.theme.strongArmy
import com.test.jetback.ui.theme.themecustom.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


@Composable
fun NumberSettingView(navController: NavController) {
    val myNumber = remember {
        mutableStateOf(phoneNumber)
    }

    Scaffold() {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "번호 설정",
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = strongArmy,
                        fontWeight = FontWeight.Bold,
                        color = TextSystemColor()
                    )
                }
            }


            TextField(
                value = myNumber.value,
                onValueChange = { myNumber.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 10.dp)
            )
        }
        Column(
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom

        ) {
            val context = LocalContext.current
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

                    phoneNumber = myNumber.value
                    val saveSystem = SaveSystem(context)

                    MainScope().launch {
                        saveSystem.save("number", phoneNumber)
                    }

                    Toast.makeText(context,"저장",Toast.LENGTH_SHORT).show()

                }) {
                Text(
                    text = "저장",
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
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NumberSettingPreview() {
    NumberSettingView(
        navController = rememberNavController()
    )
}
