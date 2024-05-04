package com.opencritic.games.details.domain.interactor

import com.opencritic.games.details.domain.Author
import com.opencritic.games.details.domain.GameDetailsRepository

class GetAuthorInteractor(
    private val repository: GameDetailsRepository,
) {
    suspend operator fun invoke(authorId: Int): Result<Author> =
        runCatching { repository.getAuthor(authorId) }
}