package ao.marco.kotlin.nasaoffline.ui.pages

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import ao.marco.kotlin.nasaoffline.R
import ao.marco.kotlin.nasaoffline.database.AppDatabase
import ao.marco.kotlin.nasaoffline.datasource.HomeDatasource
import ao.marco.kotlin.nasaoffline.datasource.state.ImageFailState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageInitialState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageLoadingState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageState
import ao.marco.kotlin.nasaoffline.datasource.state.ImageSuccessState
import ao.marco.kotlin.nasaoffline.provider.NetworkProvider
import ao.marco.kotlin.nasaoffline.ui.Details
import ao.marco.kotlin.nasaoffline.ui.NavigateController
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePage {

    companion object {
        @Composable
        fun Body(paddingValues: PaddingValues, fontFamily: FontFamily, db: AppDatabase) {
            val datasource = HomeDatasource(provider = NetworkProvider())
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 18.dp),
                content = {
                    Spacer(modifier = Modifier.height(10.dp))
                    HeaderComponent(fontFamily = fontFamily)
                    Spacer(modifier = Modifier.height(10.dp))
                    SearchComponent()
                    Column(
                        modifier = Modifier
                            .verticalScroll(enabled = true, state = ScrollState(10)),
                        content = {
                            Spacer(modifier = Modifier.height(80.dp))
                            SpotlightComponent(
                                fontFamily = fontFamily,
                                datasource = datasource,
                                db = db
                            )
                            Spacer(modifier = Modifier.height(40.dp))
                            ExploreComponent(
                                fontFamily = fontFamily,
                                datasource = datasource,
                                db = db
                            )
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    )
                }
            )
        }

        @Composable
        private fun HeaderComponent(fontFamily: FontFamily) {
            Box(modifier = Modifier.height(64.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        Text(
                            color = Color(0xFFFFFFFF),
                            fontSize = TextUnit(24F, TextUnitType.Sp),
                            fontFamily = fontFamily,
                            text = "HOME"
                        )
                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = null,
                        )
                    }
                )
            }
        }

        @Composable
        private fun SearchComponent() {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Search,
                        modifier = Modifier.size(30.dp),
                        tint = Color.White,
                        contentDescription = ""
                    )
                },
                value = "",
                shape = RoundedCornerShape(80.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White.copy(alpha = 0.3F),
                    focusedContainerColor = Color.Gray.copy(alpha = 0.1F),
                    focusedBorderColor = Color.White.copy(alpha = 0.4F),
                    cursorColor = Color.White
                ),
                onValueChange = {
                },
            )
        }

        @Composable
        private fun SpotlightComponent(
            fontFamily: FontFamily,
            datasource: HomeDatasource,
            db: AppDatabase,
        ) {
            var state: ImageState by remember {
                mutableStateOf(ImageInitialState())
            }
            LaunchedEffect(key1 = datasource) {
                if (state !is ImageSuccessState) {
                    state = ImageLoadingState()
                    db.imageDao().getImage().let {
                        state = ImageSuccessState(imageModel = it)
                    }

                    datasource.getImage().let {
                        if (it is ImageSuccessState) {
                            CoroutineScope(Dispatchers.IO).launch {
                                db.imageDao().deleteAll()
                                state = it
                                db.imageDao().insert((state as ImageSuccessState).imageModel!!)
                            }
                        }
                    }
                }
            }
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                fontFamily = fontFamily,
                color = Color.White,
                text = "SPOTLIGHT",
            )
            Card(
                modifier = Modifier.size(Constraints.Infinity.dp, 180.dp),
                border = BorderStroke(1.dp, Color.White.copy(alpha = 0.4F)),
                shape = RoundedCornerShape(8.dp),
                colors = CardColors(
                    containerColor = Color.Gray.copy(alpha = 0.1F),
                    contentColor = Color.Gray.copy(alpha = 0.1F),
                    disabledContentColor = Color.Gray.copy(alpha = 0.1F),
                    disabledContainerColor = Color.Gray.copy(alpha = 0.1F)
                ),
                content = {
                    if (state is ImageLoadingState) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                            content = {
                                CircularProgressIndicator(
                                    strokeWidth = 1.dp,
                                    color = Color.White
                                )
                            }
                        )
                    }
                    if (state is ImageSuccessState) {
                        AsyncImage(
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop,
                            model = (state as ImageSuccessState).imageModel?.url,
                            alignment = Alignment.Center,
                            contentDescription = (state as ImageSuccessState).imageModel?.title
                        )
                    }
                    if (state is ImageFailState) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                            content = {
                                Text(
                                    fontSize = TextUnit(22F, TextUnitType.Unspecified),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    text = "No image available"
                                )
                            }
                        )
                    }
                }
            )
            if (state is ImageSuccessState) {
                val model = (state as ImageSuccessState).imageModel
                model?.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(top = 10.dp),
                        color = Color.White.copy(alpha = 0.4F)
                    )
                }
                model?.explanation?.let {
                    Text(
                        text = it,
                        color = Color.White.copy(alpha = 0.8F),
                        modifier = Modifier.padding(top = 2.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Read more...",
                    color = Color.White.copy(alpha = 0.8F),
                    modifier = Modifier.padding(top = 2.dp)
                        .clickable {
                            NavigateController.navController!!.navigate(
                                Details.route.replace(
                                    "{data}",
                                    model?.id.toString()
                                )
                            )
                        },
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        @Composable
        private fun ExploreComponent(
            fontFamily: FontFamily,
            datasource: HomeDatasource,
            db: AppDatabase,
        ) {
            var state: ImageState by remember {
                mutableStateOf(ImageInitialState())
            }
            Column {
                LaunchedEffect(key1 = datasource) {
                    if (state !is ImageSuccessState) {
                        state = ImageLoadingState()
                        db.photoDao().getPhotos().let {
                            state = ImageSuccessState(photos = it)
                        }

                        datasource.getPhotos().let {

                            if (it is ImageSuccessState) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    db.photoDao().deleteAll()
                                    state = it
                                    Log.e("Data", "Data ${(state as ImageSuccessState).photos[0].imgSrc}")
                                    (state as ImageSuccessState).photos.forEach { photo ->
                                        db.photoDao().insert(photo)
                                    }
                                }
                            }
                        }
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 20.dp),
                    fontFamily = fontFamily,
                    color = Color.White,
                    text = "EXPLORE MARS",
                )
                if (state is ImageLoadingState) {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,
                        content = {
                            CircularProgressIndicator(
                                strokeWidth = 1.dp,
                                color = Color.White
                            )
                        },
                    )
                }
                if (state is ImageSuccessState) {
                    (state as ImageSuccessState).photos.let {
                        LazyRow(
                            modifier = Modifier.size(Constraints.Infinity.dp, 250.dp),
                            content = {
                                items(it.size) { it1 ->
                                    Column(
                                        modifier = Modifier
                                            .size(180.dp, 250.dp)
                                            .padding(end = 20.dp),
                                    ) {
                                        Card(
                                            modifier = Modifier.size(180.dp, 180.dp),
                                            border = BorderStroke(
                                                1.dp,
                                                Color.White.copy(alpha = 0.4F)
                                            ),
                                            shape = RoundedCornerShape(8.dp),
                                            colors = CardColors(
                                                containerColor = Color.Gray.copy(alpha = 0.1F),
                                                contentColor = Color.Gray.copy(alpha = 0.1F),
                                                disabledContentColor = Color.Gray.copy(alpha = 0.1F),
                                                disabledContainerColor = Color.Gray.copy(alpha = 0.1F)
                                            ),
                                            content = {
                                                AsyncImage(
                                                    modifier = Modifier.fillMaxSize(),
                                                    contentScale = ContentScale.Crop,
                                                    model = it[it1].imgSrc?.replace(
                                                        "http",
                                                        "https"
                                                    ),
                                                    alignment = Alignment.Center,
                                                    contentDescription = it[it1].fullName
                                                )
                                            }
                                        )
                                        Text(
                                            text = it[it1].earthDate.toString(),
                                            modifier = Modifier.padding(top = 10.dp),
                                            color = Color.White.copy(alpha = 0.4F)
                                        )
                                        Text(
                                            text = "${it[it1].fullName}",
                                            color = Color.White.copy(alpha = 0.8F),
                                            modifier = Modifier.padding(top = 2.dp),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                            }
                        )
                    }
                }

                if (state is ImageFailState) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                        content = {
                            Text(
                                fontSize = TextUnit(22F, TextUnitType.Unspecified),
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                text = "No image available"
                            )
                        }
                    )
                }
            }
        }
    }
}