package com.divisalarm.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.divisalarm.app.navigation.DivisAlarmNavGraph
import com.divisalarm.app.ui.theme.DivisAlarmTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DivisAlarmApp()
        }
    }
}

@Composable
fun DivisAlarmApp() {
    DivisAlarmTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            DivisAlarmNavGraph()
        }
    }
}
