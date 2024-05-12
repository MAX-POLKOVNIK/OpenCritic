package com.opencritic.database

import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSFileManager
import platform.Foundation.NSLibraryDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val libraryUrl: NSURL = NSFileManager.defaultManager()
        .URLsForDirectory(NSLibraryDirectory, inDomains = NSUserDomainMask)
        .last() as NSURL

    val libraryPath = libraryUrl.absoluteURL?.path ?: ""

    val dbFilePath = "$libraryPath/my_room.db"

    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
        factory =  { AppDatabase::class.instantiateImpl() }
    )
}
