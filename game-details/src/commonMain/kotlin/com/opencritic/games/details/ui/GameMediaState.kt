package com.opencritic.games.details.ui

import com.opencritic.mvvm.BaseErrorState
import com.opencritic.mvvm.BaseLoadingState
import com.opencritic.mvvm.ViewModelState

interface GameMediaState : ViewModelState {
    val titleText: String

    data class Error(
        override val titleText: String,
        override val message: String
    ) : BaseErrorState(message), GameMediaState
    data class Loading(override val titleText: String) : BaseLoadingState(), GameMediaState
    data class Content(
        val navigationTitle: String,
        override val titleText: String,
        val isTrailersVisible: Boolean,
        val trailersText: String,
        val trailers: List<TrailerItem>,
        val isScreenshotsVisible: Boolean,
        val screenshotsText: String,
        val screenshots: List<ScreenshotItem>,
    ) : GameMediaState
}