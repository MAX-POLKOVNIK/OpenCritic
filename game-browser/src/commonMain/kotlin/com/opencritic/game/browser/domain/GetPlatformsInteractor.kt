package com.opencritic.game.browser.domain

import com.opencritic.games.Platform

class GetPlatformsInteractor(
    private val browserRepository: GameBrowserRepository,
) {
    suspend operator fun invoke(): Result<List<Platform>> =
        runCatching { browserRepository.getPlatforms() }
}