package com.blez.waifuapp.presentation.waifu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.blez.waifuapp.presentation.common.Spinner

@Composable
fun WaifuScreen(navController: NavController, viewModel: WaifuViewModel = hiltViewModel()) {
    ImageBox(viewModel = viewModel, modifier = Modifier.fillMaxSize())

}

@Composable
fun ImageBox(modifier: Modifier = Modifier,viewModel: WaifuViewModel) {
    var imageURL = remember { viewModel.imageData }
    var loading = remember { viewModel.isLoading }
    var loadError = remember { viewModel.loadError }
    val sfwItems = arrayOf("waifu","neko","shinobu","megumin","bully","cuddle","awoo","kiss","lick","pat","smug","bonk","yeet","blush","smile","highfive","cry","hug","handhold","nom","bite","glomp","slap","kill","kick","happy","wink","poke","dance","cringe")
    val nsfwItems = arrayOf("waifu","neko","trap","blowjob")

    Column(modifier = modifier) {
        var selectedItem by remember { mutableStateOf("") }
        var state = remember {
            viewModel.showNSFW

        }
        var items = remember {
            if (viewModel.showNSFW.value)
                nsfwItems
            else
                sfwItems
        }
        Button(onClick = {
            viewModel.switch_NSFW(!state.value)
            viewModel.getImage()
        }, modifier = Modifier
            .padding(start = 10.dp)
            .clip(CircleShape)) {
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





@Composable
fun MySpinner(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    Spinner(
        modifier = Modifier.wrapContentSize(),
        dropDownModifier = Modifier.wrapContentSize(),
        items = items,
        selectedItem = selectedItem,
        onItemSelected = onItemSelected,
        selectedItemFactory = { modifier, item ->
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = modifier
                    .padding(8.dp)
                    .wrapContentSize()
            ) {
                Text(item, modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = com.blez.waifuapp.R.drawable.baseline_arrow_drop_down_24),
                    contentDescription = "drop down arrow"
                )
            }
        },
        dropdownItemFactory = { item, _ ->
            Text(text = item)
        }
    )
}