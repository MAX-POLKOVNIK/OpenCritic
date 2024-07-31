package com.opencritic.dashboard.ui

import com.opencritic.dashboard.domain.GameItem
import com.opencritic.mvvm.ListItem
import com.opencritic.resources.DateFormatter
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getString

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
            dateFormatter: DateFormatter,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.RecentlyReleased,
                titleText = stringProvider.getString(StringRes.str_recently_released),
                items = gameItems.map {
                    DashboardGameListItem(it, dateFormatter, onItemClick)
                },
                viewMoreText = stringProvider.getString(StringRes.str_view_more),
                onMoreClick = onMoreClick,
            )

        fun upcomingReleases(
            gameItems: List<GameItem>,
            stringProvider: StringProvider,
            dateFormatter: DateFormatter,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.UpcomingReleases,
                titleText = stringProvider.getString(StringRes.str_upcoming_releases),
                items = gameItems.map {
                    DashboardGameListItem(it, dateFormatter, onItemClick)
                },
                viewMoreText = stringProvider.getString(StringRes.str_view_more),
                onMoreClick = onMoreClick,
            )

        fun reviewedToday(
            gameItems: List<GameItem>,
            stringProvider: StringProvider,
            dateFormatter: DateFormatter,
            onItemClick: (DashboardGameListItem) -> Unit,
            onMoreClick: (DashboardSublistListItem) -> Unit,
        ): DashboardSublistListItem =
            DashboardSublistListItem(
                id = Type.ReviewedToday,
                titleText = stringProvider.getString(StringRes.str_reviewed_today),
                items = gameItems.map {
                    DashboardGameListItem(it, dateFormatter, onItemClick)
                },
                viewMoreText = stringProvider.getString(StringRes.str_reviewed_this_week),
                onMoreClick = onMoreClick,
            )
    }
}