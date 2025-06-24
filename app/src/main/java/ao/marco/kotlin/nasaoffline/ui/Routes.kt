package ao.marco.kotlin.nasaoffline.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import ao.marco.kotlin.nasaoffline.database.AppDatabase
import ao.marco.kotlin.nasaoffline.ui.pages.DetailPage
import ao.marco.kotlin.nasaoffline.ui.pages.HomePage

interface Routes {
    val route: String
    val screen: @Composable (paddingValue: PaddingValues, fontFamily: FontFamily, params: Int, db: AppDatabase?) -> Unit
}

object NavigateController {
    @SuppressLint("StaticFieldLeak")
    var navController: NavHostController? = null
}

object Home : Routes {
    override val route = "home"
    override val screen: @Composable (paddingValue: PaddingValues, fontFamily: FontFamily, params: Int, db: AppDatabase?) -> Unit =
        { paddingValues, fontFamily, params, db ->
            HomePage.Body(
                paddingValues = paddingValues, fontFamily = fontFamily, db = db!!,
            )
        }
}

object Details : Routes {
    override val route = "details/:id"
    override val screen: @Composable (paddingValue: PaddingValues, fontFamily: FontFamily, params: Int, db: AppDatabase?) -> Unit =
        { paddingValues, fontFamily, params, db ->
            DetailPage(
                paddingValues = paddingValues,
                fontFamily = fontFamily,
                data = params,
                db = db!!,
            )
        }
}