package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.TextSource
import com.opencritic.resources.text.asTextSource

data class DashboardSublistListItem(
    override val id: Type,
    val titleText: TextSource,
    val items: List<DashboardGameListItem>,
    val viewMoreText: TextSource,
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
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.RecentlyReleased,
                titleText = StringRes.str_recently_released.asTextSource(),
                items = gameItems.map {
                    DashboardGameListItem(it, onItemClick)
                },
                viewMoreText = StringRes.str_view_more.asTextSource(),
                onMoreClick = onMoreClick,
            )

        fun upcomingReleases(
            gameItems: List<GameItem>,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.UpcomingReleases,
                titleText = StringRes.str_upcoming_releases.asTextSource(),
                items = gameItems.map {
                    DashboardGameListItem(it, onItemClick)
                },
                viewMoreText = StringRes.str_view_more.asTextSource(),
                onMoreClick = onMoreClick,
            )

        fun reviewedToday(
            gameItems: List<GameItem>,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.ReviewedToday,
                titleText = StringRes.str_reviewed_today.asTextSource(),
                items = gameItems.map {
                    DashboardGameListItem(it, onItemClick)
                },
                viewMoreText = StringRes.str_reviewed_this_week.asTextSource(),
                onMoreClick = onMoreClick,
            )
    }
}