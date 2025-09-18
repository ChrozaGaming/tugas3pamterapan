package com.example.tugas3pamterapan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tugas3pamterapan.navigation.AppNavGraph
import com.example.tugas3pamterapan.ui.theme.Tugas3PAMTerapanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Tugas3PAMTerapanTheme {
                AppNavGraph()
            }
        }
    }
}
