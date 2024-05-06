package com.opencritic.main.ui

import com.opencritic.navigation.Destination
import com.opencritic.navigation.GameBrowserDestination
import com.opencritic.navigation.MainDestination
import com.opencritic.navigation.SearchDestination
import com.opencritic.navigation.YourListDestination

enum class TabType(val destination: Destination) {
    Main(MainDestination),
    Search(SearchDestination),
    Browse(GameBrowserDestination),
    YourLists(YourListDestination),
}