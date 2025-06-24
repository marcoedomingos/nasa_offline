package ao.marco.kotlin.nasaoffline.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import ao.marco.kotlin.nasaoffline.database.AppDatabase
import ao.marco.kotlin.nasaoffline.ui.NavigateController

class DetailPage {

    companion object {
        @Composable
        operator fun invoke(paddingValues: PaddingValues, fontFamily: FontFamily, data: Int, db: AppDatabase) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 18.dp),
                content = {
                    Spacer(modifier = Modifier.height(10.dp))
                    Icon(
                        Icons.AutoMirrored.Rounded.ArrowBack,
                        modifier = Modifier.size(30.dp).clickable {
                            NavigateController.navController!!.navigateUp()
                        },
                        contentDescription = null,
                        tint = Color.White,
                    )
                },
            )
        }
    }
}