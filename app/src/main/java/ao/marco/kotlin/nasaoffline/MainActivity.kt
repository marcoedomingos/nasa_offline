package ao.marco.kotlin.nasaoffline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import ao.marco.kotlin.nasaoffline.features.home.presentation.HomeScreen
import ao.marco.kotlin.nasaoffline.features.home.presentation.HomeViewModel
import ao.marco.kotlin.nasaoffline.ui.theme.NasaOfflineTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            window.statusBarColor = Color.Black.toArgb()
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
                false

            val apodState by viewModel.apodState.collectAsState()

            NasaOfflineTheme {
                Scaffold(
                    modifier = Modifier.safeDrawingPadding(),
                ) { _ ->
                    HomeScreen(
                        apodState = apodState,
                    )
                }
            }
        }
    }
}
