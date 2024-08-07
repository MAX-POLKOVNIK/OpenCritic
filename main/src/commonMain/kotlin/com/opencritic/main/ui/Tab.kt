package com.opencritic.main.ui

import com.opencritic.mvvm.ListItem
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.text.TextSource

data class Tab(
    override val id: TabType,
    val name: TextSource,
    val imageResource: IconResource,
) : ListItem<TabType>