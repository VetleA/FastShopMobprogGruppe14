package com.example.logerino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.logerino.navigation.NavigasjonMaster
import com.example.logerino.ui.theme.LogerinoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogerinoTheme {

                NavigasjonMaster()

            }
        }
    }
}
