package com.lifestylehub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.common.ui.theme.LifestyleHubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        val navigationComponent = appComponent.navigationComponentFactory.create()

        setContent {
            LifestyleHubTheme {
                AppContent(
                    bottomBarItems = navigationComponent.bottomBarItems.toList()
                        .sortedBy { it.nameId },
                    featureNavigationApis = navigationComponent.featureNavigationApis.toList()
                        .sortedByDescending { it.navigationRoute }
                )
            }
        }
    }
}