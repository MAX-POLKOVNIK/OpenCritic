package com.opencritic.search.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.search.domain.SearchItem
import com.opencritic.search.domain.SearchItemKind
import com.opencritic.search.domain.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class SearchRepositoryImpl(
    private val openCriticsApi: OpenCriticsApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SearchRepository {
    override suspend fun search(criteria: String): List<SearchItem> =
        withContext(defaultDispatcher) {
            openCriticsApi.search(criteria)
                .map { dto ->
                    SearchItem(
                        id = dto.id,
                        name = dto.name,
                        kind = SearchItemKind(dto.relation)
                    )
                }
        }
}