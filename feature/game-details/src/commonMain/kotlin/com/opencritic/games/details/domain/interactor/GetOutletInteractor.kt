package com.opencritic.games.details.domain.interactor

import com.opencritic.games.details.domain.GameDetailsRepository
import com.opencritic.games.details.domain.Outlet

class GetOutletInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(outletId: Int): Result<Outlet> =
        runCatching { repository.getOutlet(outletId) }
}