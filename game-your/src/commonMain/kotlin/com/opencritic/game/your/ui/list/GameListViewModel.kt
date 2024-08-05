package com.opencritic.game.your.ui.list

import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GetGameListInteractor
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.LinkShareRoute
import com.opencritic.resources.images.Icons
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

        loadList()
    }

    private fun loadList() {
        scope.launch {
            showLoading()

            getGameListInteractor(listId)
                .onFailure {
                    hideLoading()

                    showError(it) {
                        loadList()
                    }
                }
                .onSuccess { list ->
                    hideLoading()

                    setContent {
                        GameListContent(
                            items = list.games.map {
                                GameRowListItem(it) {
                                    onClick(it)
                                }
                            },
                            isShareVisible = true,
                            shareIconResource = Icons.share,
                            onShareClick = { shareListUrl(list.shareLink) }
                        )
                    }
                }
        }
    }

    private fun shareListUrl(url: String) {
        requireRouter()
            .navigateTo(
                LinkShareRoute(url)
            )
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