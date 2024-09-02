package com.opencritic.main.ui

import com.opencritic.mvvm.ListItem
import com.opencritic.navigation.ScreenCreator
import com.opencritic.resources.images.IconResource
import com.opencritic.resources.text.TextSource

data class Tab<InitArgs : Any>(
    override val id: TabType,
    val name: TextSource,
    val imageResource: IconResource,
    val args: InitArgs?,
    val screenCreator: ScreenCreator<InitArgs>?,
) : ListItem<TabType>