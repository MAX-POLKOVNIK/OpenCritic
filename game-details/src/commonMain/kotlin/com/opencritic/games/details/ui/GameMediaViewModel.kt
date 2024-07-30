package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.interactor.GetGameMediaInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.StringProvider
import com.opencritic.resources.StringRes
import com.opencritic.resources.getFormattedString
import com.opencritic.resources.getString
import kotlinx.coroutines.launch

class GameMediaViewModel(
    private val gameId: Long,
    private val gameName: String,
    private val getGameMediaInteractor: GetGameMediaInteractor,
    private val stringProvider: StringProvider,
    private val logger: Logger,
) : BaseViewModel<GameMediaState>() {
    override fun initialState(): GameMediaState =
        GameMediaState.Loading(stringProvider.getFormattedString(StringRes.str_game_screenshots_and_trailers, gameName))

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getGameMediaInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameMediaState.Error(
                            titleText = stringProvider.getFormattedString(StringRes.str_game_screenshots_and_trailers, gameName),
                            message = it.toString()
                        )
                    )
                    logger.log(it.toString())
                }
                .onSuccess { media ->
                    mutableState.tryEmit(
                        GameMediaState.Content(
                            navigationTitle = media.gameName,
                            titleText = stringProvider.getFormattedString(StringRes.str_game_screenshots_and_trailers, media.gameName),
                            isTrailersVisible = media.trailers.isNotEmpty(),
                            trailersText = stringProvider.getString(StringRes.str_trailers),
                            trailers =  media.trailers
                                .map { trailer ->
                                    TrailerItem(trailer) {
                                        requireRouter().navigateTo(UrlRoute(trailer.externalUrl))
                                    }
                                },
                            isScreenshotsVisible = media.screenshotUrls.isNotEmpty(),
                            screenshotsText = stringProvider.getString(StringRes.str_screenshots),
                            screenshots = media.screenshotUrls.map { ScreenshotItem(it, {}) }
                        )
                    )
                }
        }
    }
}