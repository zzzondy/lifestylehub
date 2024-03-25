package com.lifestylehub

import android.os.Bundle
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
                    // Bottom bar items и navigation apis отсортированы по одному и тому же правилу, чтобы
                    // каждомй вкладке на панеле навигации соответствовала своя навигация в фиче
                    bottomBarItems = navigationComponent.bottomBarItems.toList()
                        .sortedBy { it.navigationRoute },
                    featureNavigationApis = navigationComponent.featureNavigationApis.toList()
                        .sortedBy { it.navigationRoute }
                )
            }
        }
    }
}