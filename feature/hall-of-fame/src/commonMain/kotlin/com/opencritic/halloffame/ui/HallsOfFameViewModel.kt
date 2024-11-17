package com.opencritic.halloffame.ui

import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.halloffame.domain.GetHallsOfFameInteractor
import com.opencritic.halloffame.domain.HallOfFameGame
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.remote.images.load
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class HallsOfFameViewModel(
    private val getHallsOfFameInteractor: GetHallsOfFameInteractor,
    private val imagePreloader: ImagePreloader,
) : BaseContentViewModel<HallsOfFameContent>() {
    override fun initialState(): CommonViewModelState<HallsOfFameContent> =
        CommonViewModelState.loading(title = StringRes.str_hall_of_fame_title.asTextSource())

    override fun onCleared() {
        super.onCleared()

        imagePreloader.cancel()
    }

    override fun onStateInit() {
        super.onStateInit()

        loadHalls()
    }

    private fun loadHalls() {
        scope.launch {
            getHallsOfFameInteractor()
                .onFailure {
                    showError(it) {
                        loadHalls()
                    }
                }
                .onSuccess { halls ->
                    imagePreloader.load(halls)

                    setContent {
                        HallsOfFameContent(
                            lists = halls.map { hall ->
                                HallOfFameGameListListItem(
                                    year = hall.year,
                                    games = hall.games.map { game ->
                                        HallOfFameGameListItem(
                                            game = game,
                                            onClick = { navigateToGame(game) }
                                        )
                                    }.toImmutableList()
                                )
                            }.toImmutableList()
                        )
                    }
                }
        }
    }

    private fun navigateToGame(game: HallOfFameGame) {
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(game.id, game.name)
        )
    }
}