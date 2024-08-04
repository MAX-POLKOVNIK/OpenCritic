package com.opencritic.game.your.ui.list

import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GameList
import com.opencritic.game.your.domain.GetGameListInteractor
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class GameListViewModel(
    private val listId: String,
    private val listTitle: String,
    private val getGameListInteractor: GetGameListInteractor,
) : BaseContentViewModel<GameListContent>() {
    override fun initialState(): CommonViewModelState<GameListContent> =
        CommonViewModelState.loading(title = listTitle.asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        scope.launch {
            loading()

            getGameListInteractor(listId)
                .onFailure {
                    hideLoading()

                    error(it)
                }
                .onSuccess { list ->
                    hideLoading()

                    setContent {
                        GameListContent(
                            items = list.games.map {
                                GameRowListItem(it) {
                                    onClick(it)
                                }
                            }
                        )
                    }
                }
        }
    }

    private fun onClick(gameInList: GameInList) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(
                    gameId = gameInList.id,
                    gameName = gameInList.name,
                )
            )
    }
}