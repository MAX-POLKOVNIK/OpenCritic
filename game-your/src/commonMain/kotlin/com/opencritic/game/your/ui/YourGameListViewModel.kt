package com.opencritic.game.your.ui

import com.opencritic.game.your.domain.GetYourGameListInteractor
import com.opencritic.game.your.domain.SaveYourGameInteractor
import com.opencritic.game.your.domain.YourGame
import com.opencritic.game.your.domain.YourGameAction
import com.opencritic.logs.Logger
import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.AuthRoute
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class YourGameListViewModel(
    private val getYourGameListInteractor: GetYourGameListInteractor,
    private val saveYourGameInteractor: SaveYourGameInteractor,
    private val logger: Logger,
) : BaseViewModel<YourGameListState>() {

    private var selectedFilter: YourGameFilter = YourGameFilter.All

    override fun initialState(): YourGameListState =
        YourGameListState(
            filtersTitleText = "Filters",
            selectedFilterItem = YourGameFilterItem(selectedFilter, selectedFilter.name),
            filterItems = YourGameFilter.entries.map { YourGameFilterItem(it, it.name) },
            selectFilterItem = { onFilterItemSelected(it) },
            items = emptyList(),
            refresh = { loadGames() },
            isLoginVisible = true,
            loginText = "Login".asTextSource(),
            onLoginClick = { onLoginClick() }
        )

    override fun onStateInit() {
        super.onStateInit()

        loadGames()
    }

    private fun onLoginClick() {
        requireRouter()
            .navigateTo(AuthRoute)
    }

    private fun onFilterItemSelected(item: YourGameFilterItem) {
        if (selectedFilter == item.key)
            return

        selectedFilter = item.key

        mutableState.tryEmit(
            state.value.copy(
                selectedFilterItem = YourGameFilterItem(selectedFilter, selectedFilter.name)
            )
        )

        loadGames()
    }

    private fun loadGames() {
        logger.log("LOAD GAMES")

        scope.launch {
            getYourGameListInteractor(selectedFilter)
                .onFailure {
                    logger.log(it.toString())
                }
                .onSuccess { list ->
                    mutableState.tryEmit(
                        state.value.copy(
                            items = list.map { game ->
                                YourGameListItem(
                                    text = game.name,
                                    indicatorItem = YourGameIndicatorSmallItem(game) {
                                        onGameAction(game, it)
                                    }
                                ) { navigateToGame(game.id, game.name) }
                            }
                        )
                    )
                }
        }
    }

    private fun onGameAction(game: YourGame, action: YourGameAction) {
        val new = game.actioned(action)

        scope.launch {
            saveYourGameInteractor(new)

            loadGames()
        }
    }

    private fun navigateToGame(gameId: Long, gameName: String) {
        requireRouter().navigateTo(
            GameDetailsRoute(gameId, gameName)
        )
    }
}