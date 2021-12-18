package com.nkstudio.movingnumber.myview

import android.Manifest
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coloredShadow
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.nkstudio.movingnumber.screenmove.Screen
import com.nkstudio.movingnumber.ui.theme.strongArmy
import com.test.jetback.ui.theme.themecustom.BorderSystemColor
import com.test.jetback.ui.theme.themecustom.ButtonSystemColor
import com.test.jetback.ui.theme.themecustom.ElevationSystemColor
import com.test.jetback.ui.theme.themecustom.TextSystemColor


@Composable
@ExperimentalPermissionsApi
fun callLogPermissionView(navController: NavController)
{
    val callLogState = rememberPermissionState(permission = Manifest.permission.READ_CALL_LOG)
    val callPhoneState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)

    Scaffold() {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            //region 배너
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
                        text = "권한 설정",
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = strongArmy,
                        fontWeight = FontWeight.Bold,
                        color = TextSystemColor()
                    )
                }
            }
            Column(Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.padding(start = 30.dp, top = 30.dp),
                    text = "전화기록 내용",
                    fontSize = 30.sp
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    fontSize = 14.sp,
                    text = "이 권한은 관객의 핸드폰에 있던 번호를 마술사의 핸드폰에 던지는 연출을 할 때, 이전에 마술사가 미리 연락을 끊은 사람이 누구인지 전화 목록을 가져오는데 사용된다." +
                            "\n\n" +
                            "다음과 같은 연출을 위해 권한을 요구합니다.\n" +
                            "이 어플은 다음과 같은 연출을 위해 다음 권한을 사용하며\n그 외에의 상황에서는 악용되지 않습니다. "
                )
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    PermissionRequired(
                        permissionState = callLogState,
                        permissionNotGrantedContent = {
                            Button(
                                modifier = Modifier
                                    .padding(bottom = 60.dp)
                                    .coloredShadow(
                                        ElevationSystemColor(),
                                        alpha = 0.5f,
                                        shadowRadius = 10.dp,
                                        borderRadius = 40.dp,
                                        offsetY = 3.dp
                                    ),
                                border = BorderSystemColor(),
                                onClick = {
                                    callLogState.launchPermissionRequest()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = ButtonSystemColor()
                                ),
                            ) {
                                Text(text = "권한 허용", fontSize = 30.sp, color = TextSystemColor())
                            }
                        },
                        permissionNotAvailableContent = {  }) {

                        if(callPhoneState.hasPermission){
                            if(navController.currentDestination?.route != Screen.Home.route){
                                navController.popBackStack()
                                navController.navigate(Screen.Home.route)
                            }
                        }else{
                            if(navController.currentDestination?.route != Screen.callPhonePermission.route){
                                navController.popBackStack()
                                navController.navigate(Screen.callPhonePermission.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalPermissionsApi
@Composable
@Preview(showBackground = true)
fun callLogPermissionViewPreview()
{
    callLogPermissionView(navController = rememberNavController())
}