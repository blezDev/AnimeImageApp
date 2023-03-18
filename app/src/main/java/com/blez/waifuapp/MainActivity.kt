package com.blez.waifuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.RootGroupName
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blez.waifuapp.presentation.waifu.WaifuScreen
import com.blez.waifuapp.presentation.waifu.WaifuViewModel
import com.blez.waifuapp.ui.theme.WaifuAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaifuAppTheme {
                val navController = rememberNavController()
                val viewmodel = ViewModelProvider(this)[WaifuViewModel::class.java]
                NavHost(navController = navController, startDestination = "waifu_screen" ){
                    composable(route = "waifu_screen"){
                        WaifuScreen(navController,viewmodel)
                    }
                }

            }
        }
    }
}


