package com.opencritic.game.your.ui.list

import com.opencritic.game.your.domain.GameInList
import com.opencritic.game.your.domain.GetGameListInteractor
import com.opencritic.games.details.api.ui.GameDetailsRoute
import com.opencritic.games.list.api.GameListRoute
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.ShareLinkRoute
import com.opencritic.navigation.asShareLinkRouteArgs
import com.opencritic.remote.images.ImagePreloader
import com.opencritic.remote.images.load
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.asTextSource
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

class GameListViewModel(
    private val args: GameListRoute.InitArgs,
    private val getGameListInteractor: GetGameListInteractor,
    private val imagePreloader: ImagePreloader,
) : BaseContentViewModel<GameListContent>() {
    override fun initialState(): CommonViewModelState<GameListContent> =
        CommonViewModelState.loading(title = args.listName.asTextSource())

    private var listUrl: String? = null

    override fun onCleared() {
        super.onCleared()

        imagePreloader.cancel()
    }

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
                    imagePreloader.load(list)

                    hideLoading()

                    listUrl = list.shareLink

                    setContent {
                        GameListContent(
                            items = list.games
                                .map {
                                    GameRowListItem(it, ::onClick)
                                }
                                .toImmutableList(),
                            isActionVisible = list.shareLink.isNotBlank(),
                            actionIconResource = Icons.share,
                            onAction = ::shareListUrl
                        )
                    }
                }
        }
    }

    private fun shareListUrl() {
        val url = listUrl ?: return
        ShareLinkRoute.navigate(url.asShareLinkRouteArgs())
    }

    private fun onClick(gameInList: GameRowListItem) {
        GameDetailsRoute.navigate(
            GameDetailsRoute.InitArgs(gameInList.id, gameInList.name)
        )
    }
}