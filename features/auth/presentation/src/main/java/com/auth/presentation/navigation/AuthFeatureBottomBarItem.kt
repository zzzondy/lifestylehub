package com.auth.presentation.navigation

import com.auth.presentation.R
import com.common.navigation.BottomBarItem

class AuthFeatureBottomBarItem : BottomBarItem {

    override val navigationRoute: String = AuthFeatureScreens.navigationRoute

    override val nameId: Int = R.string.profile

    override val iconId: Int = R.drawable.person
}