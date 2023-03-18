package com.blez.waifuapp.presentation.waifu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.BoxScope.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun WaifuScreen(navController: NavController, viewModel: WaifuViewModel = hiltViewModel()) {
    ImageBox(viewModel = viewModel, modifier = Modifier.fillMaxSize())

}

@Composable
fun ImageBox(modifier: Modifier = Modifier,viewModel: WaifuViewModel) {
    var imageURL = remember { viewModel.imageData }
    var loading = remember { viewModel.isLoading }
    var loadError = remember { viewModel.loadError }


    Column(modifier = modifier) {
        var state = remember {
            viewModel.showNSFW
        }
        Button(onClick = {
            viewModel.switch_NSFW(!state.value)
            viewModel.getImage()
        }) {
            Text(text = "Show NSFW : ${state.value}")
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .aspectRatio(1f)
                .align(CenterHorizontally)
                .clickable {
                    viewModel.getImage()
                }
        ) {
            SubcomposeAsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(imageURL.value)
                .crossfade(true)
                .build(), loading = {
                CircularProgressIndicator(modifier = Modifier.scale(0.5f))
            }, contentDescription = "Image", modifier = modifier.scale(0.8f))


        }
        

    }

}