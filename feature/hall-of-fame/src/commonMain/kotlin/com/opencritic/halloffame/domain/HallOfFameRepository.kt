package com.opencritic.halloffame.domain

interface HallOfFameRepository {
    suspend fun getHallOfFame(year: Int): HallOfFameGameList
}