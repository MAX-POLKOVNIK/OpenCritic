package com.opencritic.games.details.ui

import com.opencritic.games.details.api.ui.GameMediaRoute
import com.opencritic.games.details.domain.interactor.GetGameMediaInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.navigation.asUrlRouteArgs
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.remote.images.load
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class GameMediaViewModel(
    private val args: GameMediaRoute.InitArgs,
    private val getGameMediaInteractor: GetGameMediaInteractor,
    private val logger: Logger,
    private val imagePreloader: ImagePreloader,
) : BaseViewModel<GameMediaState>() {
    override fun initialState(): GameMediaState =
        GameMediaState.Loading(
            StringRes.str_game_screenshots_and_trailers.asTextSource(args.gameName)
        )

    override fun onCleared() {
        super.onCleared()

        imagePreloader.cancel()
    }

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            getGameMediaInteractor(args.gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameMediaState.Error(
                            titleText = StringRes.str_game_screenshots_and_trailers
                                .asTextSource(args.gameName),
                            message = it.toString().asTextSource()
                        )
                    )
                    logger.log(it.toString())
                }
                .onSuccess { media ->
                    imagePreloader.load(media)

                    mutableState.tryEmit(
                        GameMediaState.Content(
                            navigationTitle = media.gameName,
                            titleText = StringRes.str_game_screenshots_and_trailers.asTextSource(media.gameName),
                            isTrailersVisible = media.trailers.isNotEmpty(),
                            trailersText = StringRes.str_trailers.asTextSource(),
                            trailers = media.trailers
                                .map { TrailerItem(it, ::onTrailerItemClick) }
                                .toImmutableList(),
                            isScreenshotsVisible = media.screenshotUrls.isNotEmpty(),
                            screenshotsText = StringRes.str_screenshots.asTextSource(),
                            screenshots = media.screenshotUrls
                                .map { ScreenshotItem(it) }
                                .toImmutableList()
                        )
                    )
                }
        }
    }

    private fun onTrailerItemClick(item: TrailerItem) {
        UrlRoute.navigate(item.externalUrl.asUrlRouteArgs())
    }
}