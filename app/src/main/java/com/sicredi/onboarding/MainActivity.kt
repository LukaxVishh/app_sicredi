package com.sicredi.onboarding


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sicredi.onboarding.ui.theme.SicrediTheme
import com.sicredi.onboarding.navigation.SicrediNavGraph
import com.sicredi.onboarding.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SicrediTheme {
                val app = application as App
// LoginViewModel não depende do repositório
                SicrediNavGraph(repository = app.repository)
            }
        }
    }
}