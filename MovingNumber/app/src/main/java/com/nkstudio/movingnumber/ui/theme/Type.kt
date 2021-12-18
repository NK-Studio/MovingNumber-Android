package com.nkstudio.movingnumber.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nkstudio.movingnumber.R

val strongArmy = FontFamily(
        Font(R.font.strongarmy_medium, FontWeight.Medium),
        Font(R.font.strongarmy_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        )
)