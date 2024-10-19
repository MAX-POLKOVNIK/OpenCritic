package com.opencritic.search.domain

class SearchInteractor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(criteria: String): Result<List<SearchItem>> =
        runCatching {
            repository.search(criteria)
        }
}