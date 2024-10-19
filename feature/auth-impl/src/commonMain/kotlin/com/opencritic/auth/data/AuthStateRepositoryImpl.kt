package com.opencritic.auth.data

import com.opencritic.api.OpenCriticsApi
import com.opencritic.auth.api.domain.AuthState
import com.opencritic.auth.domain.AuthStateRepository
import com.opencritic.database.preferences.UserPreferencesDao
import com.opencritic.database.preferences.UserPreferencesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class AuthStateRepositoryImpl(
    private val userPreferencesDao: UserPreferencesDao,
    private val openCriticsApi: OpenCriticsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AuthStateRepository {
    override suspend fun getState(): AuthState =
        withContext(dispatcher) {
            val userPreferencesEntity = getOrCreate()

            AuthState(
                authToken = userPreferencesEntity.authToken ,
                isOfflineMode = userPreferencesEntity.isOfflineModeEnabled
            )
        }

    override suspend fun setToken(token: String?) =
        withContext(dispatcher) {
            val entity = getOrCreate()

            userPreferencesDao.insert(
                entity.copy(authToken = token)
            )
        }

    override suspend fun setOfflineMode(isEnabled: Boolean) {
        withContext(dispatcher) {
            val entity = getOrCreate()

            userPreferencesDao.insert(
                entity.copy(isOfflineModeEnabled = isEnabled)
            )
        }
    }

    override suspend fun getProfileId(token: String): Int =
        withContext(dispatcher) {
            openCriticsApi.getProfile(token).id
        }

    private suspend fun getOrCreate(): UserPreferencesEntity =
        userPreferencesDao.get() ?: run {
            UserPreferencesEntity()
                .also { userPreferencesDao.insert(it) }
        }
}