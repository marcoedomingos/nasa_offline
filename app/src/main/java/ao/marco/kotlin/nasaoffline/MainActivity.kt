package ao.marco.kotlin.nasaoffline


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import ao.marco.kotlin.nasaoffline.database.AppDatabase
import ao.marco.kotlin.nasaoffline.ui.Home
import ao.marco.kotlin.nasaoffline.ui.Details
import ao.marco.kotlin.nasaoffline.ui.NavigateController
import ao.marco.kotlin.nasaoffline.ui.Routes

class MainActivity : ComponentActivity() {
    private val fontFamily: FontFamily = FontFamily(
        Font(
            R.font.nasalization_regular,
            FontWeight.Bold
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Material(
                content = { Body() }
            )
        }
    }

    @Composable
    fun Material(content: @Composable () -> Unit) {
        MaterialTheme(
            content = content,
        )
    }

    @Composable
    fun Body() {
        val screen: Routes by remember {
            mutableStateOf(Home)
        }
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "offline").build()
        NavigateController.navController = rememberNavController()
        Scaffold(
            modifier = Modifier.safeDrawingPadding(),
            containerColor = Color(0xFF000000)
        ) { paddingValues ->
            NavHost(
                navController = NavigateController.navController!!,
                startDestination = screen.route,
                modifier = Modifier.padding(paddingValues),
            ) {
                composable(route = Home.route) {
                    Home.screen(paddingValues, fontFamily, 0, db)
                }
                composable(route = Details.route)  {
                    it.arguments?.getInt("data")
                        ?.let { it1 -> Details.screen(paddingValues, fontFamily, it1, db) }
                }
            }
        }
    }
}