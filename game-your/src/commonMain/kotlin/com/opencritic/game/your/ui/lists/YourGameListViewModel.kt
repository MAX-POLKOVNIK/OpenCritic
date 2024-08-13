package com.opencritic.game.your.ui.lists

import com.opencritic.auth.domain.GetAuthStateInteractor
import com.opencritic.auth.domain.SetOfflineModeInteractor
import com.opencritic.game.your.domain.GetListsInteractor
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.AboutRoute
import com.opencritic.navigation.AuthRoute
import com.opencritic.navigation.GameListRoute
import com.opencritic.navigation.LinkShareRoute
import com.opencritic.resources.images.Icons
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.launch

class YourGameListViewModel(
    private val getListsInteractor: GetListsInteractor,
    private val getAuthStateInteractor: GetAuthStateInteractor,
    private val setOfflineModeInteractor: SetOfflineModeInteractor,
) : BaseContentViewModel<YourGameListState>() {

    override fun initialState(): CommonViewModelState<YourGameListState> =
        CommonViewModelState.loading(title = StringRes.str_tab_your_list.asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        loadLists()
    }

    private fun loadLists(shouldShowLoading: Boolean = true) {
        scope.launch {
            if (shouldShowLoading) {
                showLoading()
            }

            val authResult = getAuthStateInteractor()

            if (authResult.isFailure) {
                showError(requireNotNull(authResult.exceptionOrNull())) {
                    loadLists()
                }

                return@launch
            }

            val isLoggedIn = authResult.getOrThrow().shouldAskToLogin

            if (isLoggedIn) {
                setContent {
                    YourGameListState(
                        items = emptyList(),
                        onLoginClick = { navigateToAuth() },
                        isLoginVisible = true,
                        loginText = StringRes.str_game_lists_login_to_profile.asTextSource(),
                        useOfflineText = StringRes.str_game_lists_use_offline.asTextSource(),
                        onUseOfflineClick = { onUseOfflineClick() },
                        refresh = { loadLists(shouldShowLoading = false) },
                        isActionVisible = true,
                        actionIconResource = Icons.info,
                        onAction = { navigateToAbout() }
                    )
                }
            } else {
                getListsInteractor()
                    .onFailure {
                        showError(it) {
                            loadLists()
                        }
                    }
                    .onSuccess { gameLists ->
                        hideLoading()
                        setContent {
                            YourGameListState(
                                items = gameLists.map {
                                    GameListListItem(
                                        gameList = it,
                                        onClick = {
                                            navigateToList(it.id, it.name)
                                        },
                                        onShareClick = { navigateToShare(it.shareLink) },
                                        onEditClick = {}
                                    )
                                },
                                onLoginClick = {},
                                isLoginVisible = false,
                                loginText = "".asTextSource(),
                                refresh = { loadLists(shouldShowLoading = false) },
                                isActionVisible = true,
                                actionIconResource = Icons.info,
                                onAction = { navigateToAbout() },
                                useOfflineText = "Use offline lists".asTextSource(),
                                onUseOfflineClick = { onUseOfflineClick() },
                            )
                        }
                    }
            }
        }
    }

    private fun onUseOfflineClick() {
        scope.launch {
            setOfflineModeInteractor(isEnabled = true)

            loadLists(shouldShowLoading = false)
        }
    }

    private fun navigateToList(listId: String, listName: String) {
        requireRouter()
            .navigateTo(
                GameListRoute(listId, listName)
            )
    }

    private fun navigateToShare(url: String) {
        requireRouter().navigateTo(LinkShareRoute(url))
    }

    private fun navigateToAuth() {
        requireRouter().navigateTo(AuthRoute)
    }

    private fun navigateToAbout() {
        requireRouter().navigateTo(AboutRoute)
    }
}