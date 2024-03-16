package com.feature_main.presentation.navigation

import com.common.navigation.BottomBarItem
import com.feature_main.presentation.R

class MainFeatureBottomBarItem : BottomBarItem {

    override val navigationRoute: String = MainFeatureScreens.navigationRoute

    override val nameId: Int = R.string.main

    override val iconId: Int = R.drawable.home
}