package ao.marco.kotlin.nasaoffline

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ao.marco.kotlin.nasaoffline.ui.theme.HomePage

class MainActivity : ComponentActivity() {
    private val fontFamily: FontFamily = FontFamily(
        Font(
            R.font.nasalization_regular,
            FontWeight.Bold
        )
    );

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Material(
                content = { Body() }
            )
        }
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Material(content: @Composable () -> Unit) {
        MaterialTheme(
            content = content,
        )
    }

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun Body() {
        Scaffold(
            modifier = Modifier.safeDrawingPadding(),
            containerColor = Color(0xFF000000)
        ) { paddingValues ->
            HomePage.Body(paddingValues = paddingValues, fontFamily = fontFamily)
        }
    }
}