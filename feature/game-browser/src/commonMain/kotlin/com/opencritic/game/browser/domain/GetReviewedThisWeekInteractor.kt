package com.opencritic.game.browser.domain

class GetReviewedThisWeekInteractor(
    private val repository: GameBrowserRepository,
) {
    suspend operator fun invoke(): Result<List<BrowseGame>> =
        runCatching { repository.getReviewedThisWeek() }
}