package com.opencritic.resources.images

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Adjust
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import com.opencritic.resources.R

actual object Icons {
    actual val tabMain: IconResource = Icons.Outlined.Dashboard.iconRes()
    actual val tabNews: IconResource = Icons.Outlined.Newspaper.iconRes()
    actual val tabSearch: IconResource = Icons.Outlined.Search.iconRes()
    actual val tabBrowse: IconResource = Icons.Outlined.SportsEsports.iconRes()
    actual val tabCalendar: IconResource = Icons.Outlined.CalendarMonth.iconRes()
    actual val tabYourList: IconResource = Icons.Outlined.Star.iconRes()

    actual val gameActionWant: IconResource = Icons.Outlined.Add.iconRes()
    actual val gameActionPlayed: IconResource = Icons.Outlined.Done.iconRes()
    actual val gameActionFavorite: IconResource = Icons.Filled.Favorite.iconRes()

    actual val hashTag: IconResource = Icons.Outlined.Tag.iconRes()
    actual val chartPie: IconResource = Icons.Outlined.PieChart.iconRes()
    actual val bullseye: IconResource = Icons.Outlined.Adjust.iconRes()
    actual val thumbUp: IconResource = Icons.Outlined.ThumbUp.iconRes()

    actual val xbox: IconResource = R.drawable.ic_xbox.iconRes()
    actual val playstation: IconResource = R.drawable.ic_playstation.iconRes()
    actual val home: IconResource = Icons.Outlined.Home.iconRes()

    actual val starFilled: IconResource = Icons.Filled.Star.iconRes()
    actual val starHalf: IconResource = Icons.AutoMirrored.Filled.StarHalf.iconRes()
    actual val star: IconResource = Icons.Outlined.StarOutline.iconRes()

    actual val heart: IconResource = Icons.Filled.Favorite.iconRes()
    actual val calendar: IconResource = Icons.Outlined.CalendarMonth.iconRes()
    actual val warning: IconResource = Icons.Rounded.Warning.iconRes()

    actual val arrowBack: IconResource = Icons.AutoMirrored.Filled.ArrowBack.iconRes()
    actual val arrowDown: IconResource = Icons.Sharp.KeyboardArrowDown.iconRes()

    actual val share: IconResource = Icons.Outlined.Share.iconRes()

    actual val info: IconResource = Icons.Outlined.Info.iconRes()

    actual val videoPlay: IconResource = Icons.Outlined.PlayCircle.iconRes()
}