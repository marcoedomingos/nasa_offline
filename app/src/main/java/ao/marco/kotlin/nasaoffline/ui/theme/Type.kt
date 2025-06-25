package ao.marco.kotlin.nasaoffline.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ao.marco.kotlin.nasaoffline.R

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(
            Font(
                R.font.nasalization_regular,
                FontWeight.Bold

            )
        ), fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(
            Font(
                R.font.nasalization_regular,
                FontWeight.Bold

            )
        ),
    )
)