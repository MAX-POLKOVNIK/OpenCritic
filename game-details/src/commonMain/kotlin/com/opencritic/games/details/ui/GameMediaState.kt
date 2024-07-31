package com.opencritic.games.details.ui

import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState
import com.opencritic.resources.text.TextSource

interface GameMediaState : ViewModelState {
    val titleText: TextSource

    data class Error(
        override val titleText: TextSource,
        override val message: String
    ) : BaseErrorState(message), GameMediaState
    data class Loading(override val titleText: TextSource) : BaseLoadingState(), GameMediaState
    data class Content(
        val navigationTitle: String,
        override val titleText: TextSource,
        val isTrailersVisible: Boolean,
        val trailersText: TextSource,
        val trailers: List<TrailerItem>,
        val isScreenshotsVisible: Boolean,
        val screenshotsText: TextSource,
        val screenshots: List<ScreenshotItem>,
    ) : GameMediaState
}