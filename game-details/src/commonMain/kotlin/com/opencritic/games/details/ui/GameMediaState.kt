package com.opencritic.games.details.ui

import com.opencritic.mvvm.ViewModelState

interface GameMediaState : ViewModelState {
    data class Error(val message: String) : GameMediaState
    data object Loading : GameMediaState
    data class Content(
        val navigationTitle: String,
        val titleText: String,
        val isTrailersVisible: Boolean,
        val trailersText: String,
        val trailers: List<TrailerItem>,
        val isScreenshotsVisible: Boolean,
        val screenshotsText: String,
        val screenshots: List<ScreenshotItem>,
    ) : GameMediaState
}