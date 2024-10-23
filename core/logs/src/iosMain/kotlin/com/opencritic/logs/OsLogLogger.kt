package com.opencritic.logs

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ptr
import platform.darwin.OS_LOG_DEFAULT
import platform.darwin.OS_LOG_TYPE_DEBUG
import platform.darwin.OS_LOG_TYPE_DEFAULT
import platform.darwin.OS_LOG_TYPE_ERROR
import platform.darwin.OS_LOG_TYPE_FAULT
import platform.darwin.__dso_handle
import platform.darwin._os_log_internal

@OptIn(ExperimentalForeignApi::class)
class OsLogLogger : Logger {
    override fun logDebug(message: String) {
        _os_log_internal(
            __dso_handle.ptr,
            OS_LOG_DEFAULT,
            OS_LOG_TYPE_DEBUG,
            message,
        )
    }

    override fun logInfo(message: String) {
        _os_log_internal(
            __dso_handle.ptr,
            OS_LOG_DEFAULT,
            OS_LOG_TYPE_DEFAULT,
            message,
        )
    }

    override fun logWarn(message: String) {
        _os_log_internal(
            __dso_handle.ptr,
            OS_LOG_DEFAULT,
            OS_LOG_TYPE_ERROR,
            message,
        )
    }

    override fun logError(message: String) {
        _os_log_internal(
            __dso_handle.ptr,
            OS_LOG_DEFAULT,
            OS_LOG_TYPE_FAULT,
            message,
        )
    }
}