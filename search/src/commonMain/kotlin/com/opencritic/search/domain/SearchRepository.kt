package com.opencritic.search.domain

interface SearchRepository {
    suspend fun search(criteria: String): List<SearchItem>
}