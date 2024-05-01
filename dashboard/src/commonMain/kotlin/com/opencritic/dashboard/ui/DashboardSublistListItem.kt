package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.ImageResourceProvider
import com.opencritic.resources.StringResourceProvider
import com.opencritic.resources.get

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
            stringResourceProvider: StringResourceProvider,
            imageResourceProvider: ImageResourceProvider,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.RecentlyReleased,
                titleText = stringResourceProvider.recentlyReleased.get(stringResourceProvider),
                items = gameItems.map {
                    DashboardGameListItem(it, imageResourceProvider, onItemClick)
                },
                viewMoreText = stringResourceProvider.viewMore.get(stringResourceProvider),
                onMoreClick = onMoreClick,
            )

        fun upcomingReleases(
            gameItems: List<GameItem>,
            stringResourceProvider: StringResourceProvider,
            imageResourceProvider: ImageResourceProvider,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.UpcomingReleases,
                titleText = stringResourceProvider.upcomingReleases.get(stringResourceProvider),
                items = gameItems.map {
                    DashboardGameListItem(it, imageResourceProvider, onItemClick)
                },
                viewMoreText = stringResourceProvider.viewMore.get(stringResourceProvider),
                onMoreClick = onMoreClick,
            )

        fun reviewedToday(
            gameItems: List<GameItem>,
            stringResourceProvider: StringResourceProvider,
            imageResourceProvider: ImageResourceProvider,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.ReviewedToday,
                titleText = stringResourceProvider.reviewedToday.get(stringResourceProvider),
                items = gameItems.map {
                    DashboardGameListItem(it, imageResourceProvider, onItemClick)
                },
                viewMoreText = stringResourceProvider.reviewedThisWeek.get(stringResourceProvider),
                onMoreClick = onMoreClick,
            )
    }
}