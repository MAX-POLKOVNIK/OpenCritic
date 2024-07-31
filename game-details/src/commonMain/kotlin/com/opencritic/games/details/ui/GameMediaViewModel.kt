package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.interactor.GetGameMediaInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class GameMediaViewModel(
    private val gameId: Long,
    private val gameName: String,
    private val getGameMediaInteractor: GetGameMediaInteractor,
    private val logger: Logger,
) : BaseViewModel<GameMediaState>() {
    override fun initialState(): GameMediaState =
        GameMediaState.Loading(StringRes.str_game_screenshots_and_trailers.asTextSource(gameName))

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getGameMediaInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameMediaState.Error(
                            titleText = StringRes.str_game_screenshots_and_trailers.asTextSource(gameName),
                            message = it.toString()
                        )
                    )
                    logger.log(it.toString())
                }
                .onSuccess { media ->
                    mutableState.tryEmit(
                        GameMediaState.Content(
                            navigationTitle = media.gameName,
                            titleText = StringRes.str_game_screenshots_and_trailers.asTextSource(media.gameName),
                            isTrailersVisible = media.trailers.isNotEmpty(),
                            trailersText = StringRes.str_trailers.asTextSource(),
                            trailers =  media.trailers
                                .map { trailer ->
                                    TrailerItem(trailer) {
                                        requireRouter().navigateTo(UrlRoute(trailer.externalUrl))
                                    }
                                },
                            isScreenshotsVisible = media.screenshotUrls.isNotEmpty(),
                            screenshotsText = StringRes.str_screenshots.asTextSource(),
                            screenshots = media.screenshotUrls.map { ScreenshotItem(it, {}) }
                        )
                    )
                }
        }
    }
}