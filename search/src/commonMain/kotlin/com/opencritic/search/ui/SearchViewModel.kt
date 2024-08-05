package com.opencritic.search.ui

import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.AuthorReviewsRoute
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.navigation.OutletReviewsRoute
import com.opencritic.resources.MR
import com.opencritic.resources.text.StringRes
import com.opencritic.resources.text.asTextSource
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
) : BaseContentViewModel<SearchState>() {
    override fun initialState(): CommonViewModelState<SearchState> =
        CommonViewModelState.content(
            title = StringRes.str_tab_search.asTextSource(),
            content = SearchState(
                searchText = "",
                searchHint = MR.strings.str_search_hint.asTextSource(),
                searchListItemsState = SearchItemsState.Empty(MR.strings.str_search_hint.asTextSource()),
                onSearchChanged = { _, criteria -> criteriaFlow.tryEmit(criteria) }
            )
        )

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val criteriaFlow = MutableStateFlow("")
        .apply {
            this.debounce(300.milliseconds)
                .filter {
                    when {
                        it.isBlank() -> {
                            mutableState.tryEmit(initialState())

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
                    updateContentIfSet {
                        SearchState(
                            searchText = searchText,
                            searchHint = MR.strings.str_search_hint.asTextSource(),
                            onSearchChanged = { _, c -> onCriteriaChanged(c) },
                            searchListItemsState = SearchItemsState.Error(it.toString().asTextSource())
                        )
                    }
                }
                .onEach { result ->
                    updateContentIfSet {
                        SearchState(
                            searchText = searchText,
                            searchHint = StringRes.str_search_hint.asTextSource(),
                            onSearchChanged = { _, c -> onCriteriaChanged(c) },
                            searchListItemsState = SearchItemsState.Content(
                                result.map { item ->
                                    SearchListItem(
                                        item = item,
                                    ) {
                                        onSearchItemClick(item)
                                    }
                                }
                            )
                        )
                    }
                }
                .launchIn(scope)
        }


    private fun onCriteriaChanged(newCriteria: String) {
        updateContentIfSet {
            copy(searchText = newCriteria)
        }
        criteriaFlow.tryEmit(newCriteria)
    }

    private fun onSearchItemClick(item: SearchItem) =
        when (item.kind) {
            SearchItemKind.Game ->
                requireRouter().navigateTo(GameDetailsRoute(item.id, item.name))
            SearchItemKind.Critic ->
                requireRouter().navigateTo(AuthorReviewsRoute(item.id.toInt(), item.name))
            SearchItemKind.Outlet ->
                requireRouter().navigateTo(OutletReviewsRoute(item.id.toInt(), item.name))
        }
}