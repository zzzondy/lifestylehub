package com.planner.presentation.navigation

import com.common.navigation.BottomBarItem
import com.planner.presentation.R

class PlannerFeatureBottomBarItem : BottomBarItem {
    override val navigationRoute: String = PlannerFeatureScreens.navigationRoute

    override val nameId: Int = R.string.planner

    override val iconId: Int = R.drawable.dashboard
}