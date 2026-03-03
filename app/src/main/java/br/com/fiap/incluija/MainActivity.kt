package br.com.fiap.incluija

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.incluija.navigation.AppNavigation
import br.com.fiap.incluija.ui.theme.IncluijaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IncluijaTheme {
                AppNavigation()
            }
        }
    }
}
