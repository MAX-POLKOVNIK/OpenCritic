package com.opencritic.game.your.ui.list

import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GetGameListInteractor
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.list.api.GameListRoute
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.ShareLinkRoute
import com.opencritic.navigation.asShareLinkRouteArgs
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class GameListViewModel(
    private val args: GameListRoute.InitArgs,
    private val getGameListInteractor: GetGameListInteractor,
) : BaseContentViewModel<GameListContent>() {
    override fun initialState(): CommonViewModelState<GameListContent> =
        CommonViewModelState.loading(title = args.listName.asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        loadList()
    }

    private fun loadList() {
        scope.launch {
            showLoading()

            getGameListInteractor(args.listId)
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
                            isActionVisible = list.shareLink.isNotBlank(),
                            actionIconResource = Icons.share,
                            onAction = { shareListUrl(list.shareLink) }
                        )
                    }
                }
        }
    }

    private fun shareListUrl(url: String) {
        ShareLinkRoute.navigate(url.asShareLinkRouteArgs())
    }

    private fun onClick(gameInList: GameInList) {
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(gameInList.id, gameInList.name)
        )
    }
}