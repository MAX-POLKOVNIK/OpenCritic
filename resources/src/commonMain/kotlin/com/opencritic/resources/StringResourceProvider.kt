package com.opencritic.resources

interface StringResourceProvider {
    fun get(stringResource: StringResource): String

    val tabMain: StringResource
    val tabSearch: StringResource
    val tabBrowse: StringResource
    val tabCalendar: StringResource
    val tabYourList: StringResource

    val popularGames: StringResource
    val popularGamesDescription: StringResource
}

fun StringResource.get(provider: StringResourceProvider): String =
    provider.get(this)