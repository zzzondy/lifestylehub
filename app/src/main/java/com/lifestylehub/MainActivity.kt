package com.lifestylehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.common.ui.theme.LifestyleHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navigationComponent = appComponent.navigationComponentFactory.create()

        setContent {
            LifestyleHubTheme {
                AppContent(
                    bottomBarItems = navigationComponent.bottomBarItems.toList(),
                    featureNavigationApis = navigationComponent.featureNavigationApis.toList()
                )
            }
        }
    }
}