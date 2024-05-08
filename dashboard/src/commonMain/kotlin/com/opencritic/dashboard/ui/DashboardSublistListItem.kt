package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringProvider

data class DashboardSublistListItem(
    override val id: Type,
    val titleText: String,
    val items: List<DashboardGameListItem>,
    val viewMoreText: String,
    private val onMoreClick: (DashboardSublistListItem) -> Unit,
) : ListItem<DashboardSublistListItem.Type> {

    fun moreClick() =
        onMoreClick(this)

    enum class Type {
        RecentlyReleased,
        UpcomingReleases,
        ReviewedToday,
    }

    companion object {
        fun recentlyReleased(
            gameItems: List<GameItem>,
            stringProvider: StringProvider,
            imageResourceProvider: ImageResourceProvider,
            dateFormatter: DateFormatter,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.RecentlyReleased,
                titleText = stringProvider.recentlyReleased,
                items = gameItems.map {
                    DashboardGameListItem(it, imageResourceProvider, dateFormatter, onItemClick)
                },
                viewMoreText = stringProvider.viewMore,
                onMoreClick = onMoreClick,
            )

        fun upcomingReleases(
            gameItems: List<GameItem>,
            stringProvider: StringProvider,
            imageResourceProvider: ImageResourceProvider,
            dateFormatter: DateFormatter,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.UpcomingReleases,
                titleText = stringProvider.upcomingReleases,
                items = gameItems.map {
                    DashboardGameListItem(it, imageResourceProvider, dateFormatter, onItemClick)
                },
                viewMoreText = stringProvider.viewMore,
                onMoreClick = onMoreClick,
            )

        fun reviewedToday(
            gameItems: List<GameItem>,
            stringProvider: StringProvider,
            imageResourceProvider: ImageResourceProvider,
            dateFormatter: DateFormatter,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.ReviewedToday,
                titleText = stringProvider.reviewedToday,
                items = gameItems.map {
                    DashboardGameListItem(it, imageResourceProvider, dateFormatter, onItemClick)
                },
                viewMoreText = stringProvider.reviewedThisWeek,
                onMoreClick = onMoreClick,
            )
    }
}