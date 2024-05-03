package com.opencritic.games.details.ui

import com.opencritic.games.details.domain.interactor.GetGameMediaInteractor
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.UrlRoute
import com.opencritic.resources.StringProvider
import kotlinx.coroutines.launch

class GameMediaViewModel(
    private val gameId: Long,
    private val getGameMediaInteractor: GetGameMediaInteractor,
    private val stringProvider: StringProvider,
    private val logger: Logger,
) : BaseViewModel<GameMediaState>() {
    override val initialState: GameMediaState =
        GameMediaState.Loading

    init {
        scope.launch {
            getGameMediaInteractor(gameId)
                .onFailure {
                    mutableState.tryEmit(
                        GameMediaState.Error(
                            message = it.toString()
                        )
                    )
                    logger.log(it.toString())
                }
                .onSuccess { media ->
                    mutableState.tryEmit(
                        GameMediaState.Content(
                            navigationTitle = media.gameName,
                            titleText = stringProvider.gameScreenshotsAndTrailers(media.gameName),
                            isTrailersVisible = media.trailers.isNotEmpty(),
                            trailersText = stringProvider.trailers,
                            trailers =  media.trailers
                                .map { trailer ->
                                    TrailerItem(trailer) {
                                        requireRouter().navigateTo(UrlRoute(trailer.externalUrl))
                                    }
                                },
                            isScreenshotsVisible = media.screenshotUrls.isNotEmpty(),
                            screenshotsText = stringProvider.screenshots,
                            screenshots = media.screenshotUrls.map { ScreenshotItem(it, {}) }
                        )
                    )
                }
        }
    }
}