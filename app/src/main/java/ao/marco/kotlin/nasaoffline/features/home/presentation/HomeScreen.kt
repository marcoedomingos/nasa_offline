package ao.marco.kotlin.nasaoffline.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ao.marco.kotlin.nasaoffline.R
import ao.marco.kotlin.nasaoffline.features.home.domain.entity.PlanetaryApodEntity
import ao.marco.kotlin.nasaoffline.helpers.DataUiState
import ao.marco.kotlin.nasaoffline.ui.theme.Typography
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen(
    apodState: DataUiState<PlanetaryApodEntity>
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        when (apodState) {
            is DataUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is DataUiState.Success -> {
                val data = apodState.data
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    item {
                        Text(
                            style = Typography.titleLarge, text = "HOME"
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(data.hdurl)
                                .crossfade(true)
                                .placeholder(R.drawable.placeholder)
                                .error(R.drawable.placeholder)
                                .build(),
                            contentDescription = data.title,
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp)
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Text(
                            text = data.date,
                            style = Typography.displayMedium,
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Text(
                            text = data.title,
                            style = Typography.displayMedium,
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Text(
                            text = data.explanation,
                            style = Typography.displayMedium,
                        )
                    }
                }
            }

            is DataUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${apodState.exception.message}",
                        style = Typography.displayMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
