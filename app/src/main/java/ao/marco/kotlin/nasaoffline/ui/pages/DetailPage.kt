package ao.marco.kotlin.nasaoffline.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ao.marco.kotlin.nasaoffline.database.AppDatabase
import ao.marco.kotlin.nasaoffline.datasource.state.ImageInitialState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageLoadingState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageSuccessState
import ao.marco.kotlin.nasaoffline.ui.NavigateController
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailPage {

    companion object {
        @Composable
        operator fun invoke(
            paddingValues: PaddingValues,
            db: AppDatabase
        ) {
            var state: ImageState by remember {
                mutableStateOf(ImageInitialState())
            }
            LaunchedEffect(key1 = db) {
                state = ImageLoadingState()
                CoroutineScope(Dispatchers.IO).launch {
                    db.imageDao().getImage().let {
                        state = ImageSuccessState(imageModel = it)
                    }
                }
            }
            if (state is ImageLoadingState) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 5.dp,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                )
            } else {
                Box(
                    content = {
                        if (state is ImageSuccessState) {
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                model = (state as ImageSuccessState).imageModel?.url,
                                alignment = Alignment.Center,
                                contentDescription = (state as ImageSuccessState).imageModel?.title
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(paddingValues)
                                    .padding(horizontal = 18.dp),
                                content = {
                                    (state as ImageSuccessState).imageModel?.title?.let {
                                        Text(
                                            text = it,
                                            modifier = Modifier.padding(top = 10.dp),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 30.sp,
                                            color = Color.White,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(5.dp))
                                    (state as ImageSuccessState).imageModel?.explanation?.let {
                                        Text(
                                            text = it,
                                            color = Color.White.copy(alpha = 0.8F),
                                            modifier = Modifier.padding(top = 2.dp),
                                            textAlign = TextAlign.Justify,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                })
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .padding(horizontal = 18.dp),
                                content = {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Icon(
                                        Icons.AutoMirrored.Rounded.ArrowBack,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clickable {
                                                NavigateController.navController!!.navigateUp()
                                            },
                                        contentDescription = null,
                                        tint = Color.White,
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                })
                        }
                    })
            }
        }
    }
}