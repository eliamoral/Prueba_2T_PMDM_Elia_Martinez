package com.unirfp.app_retrofit_elia_martinez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.unirfp.app_retrofit_elia_martinez.ui.screens.ProductoScreen
import com.unirfp.app_retrofit_elia_martinez.ui.theme.App_Retrofit_Elia_MartinezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_Retrofit_Elia_MartinezTheme {
                ProductoScreen()
            }
        }
    }
}
