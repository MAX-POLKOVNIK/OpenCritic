package com.opencritic.main.ui

import com.opencritic.mvvm.ListItem
import com.opencritic.resources.IconResource

data class Tab(
    override val id: TabType,
    val name: String,
    val imageResource: IconResource,
) : ListItem<TabType>