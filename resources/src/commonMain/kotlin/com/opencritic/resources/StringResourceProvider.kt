package com.opencritic.resources

interface StringResourceProvider {
    fun get(stringResource: StringResource): String

    val popularGames: StringResource
    val popularGamesDescription: StringResource
}