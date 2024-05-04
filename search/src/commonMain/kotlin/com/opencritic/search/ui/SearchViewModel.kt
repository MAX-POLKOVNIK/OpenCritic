package com.opencritic.search.ui

import com.opencritic.mvvm.BaseViewModel
import com.opencritic.navigation.AuthorReviewsRoute
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.OutletReviewsRoute
import com.opencritic.resources.StringProvider
import com.opencritic.search.domain.SearchInteractor
import com.opencritic.search.domain.SearchItem
import com.opencritic.search.domain.SearchItemKind
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlin.time.Duration.Companion.milliseconds

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val stringProvider: StringProvider,
) : BaseViewModel<SearchState>() {
    override val initialState: SearchState =
        SearchState(
            searchText = "",
            searchHint = stringProvider.searchHint,
            searchListItemsState = SearchItemsState.Empty(stringProvider.searchHint),
            onSearchChanged = { _, criteria -> criteriaFlow.tryEmit(criteria) }
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val criteriaFlow = MutableStateFlow("")
        .apply {
            this.debounce(300.milliseconds)
                .filter {
                    when {
                        it.isBlank() -> {
                            mutableState.tryEmit(initialState)

                            false
                        }
                        else -> true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    flow {
                        emit(searchInteractor.invoke(it))
                    }
                }
                .flowOn(Dispatchers.IO)
                .map { it.getOrThrow() }
                .catch {
                    mutableState.tryEmit(
                        SearchState(
                            searchText = state.value.searchText,
                            searchHint = stringProvider.searchHint,
                            onSearchChanged = { _, c -> onCriteriaChanged(c) },
                            searchListItemsState = SearchItemsState.Error(it.toString())
                        )
                    )
                }
                .onEach { result ->
                    mutableState.tryEmit(
                        SearchState(
                            searchText = state.value.searchText,
                            searchHint = stringProvider.searchHint,
                            onSearchChanged = { _, c -> onCriteriaChanged(c) },
                            searchListItemsState = SearchItemsState.Content(
                                result.map { item ->
                                    SearchListItem(
                                        item = item,
                                        stringProvider = stringProvider,
                                    ) {
                                        onSearchItemClick(item)
                                    }
                                }
                            )
                        )
                    )
                }
                .launchIn(scope)
        }


    private fun onCriteriaChanged(newCriteria: String) {
        criteriaFlow.tryEmit(newCriteria)
    }

    private fun onSearchItemClick(item: SearchItem) =
        when (item.kind) {
            SearchItemKind.Game ->
                requireRouter().navigateTo(GameDetailsRoute(item.id))
            SearchItemKind.Critic ->
                requireRouter().navigateTo(AuthorReviewsRoute(item.id.toInt()))
            SearchItemKind.Outlet ->
                requireRouter().navigateTo(OutletReviewsRoute(item.id.toInt()))
        }
}