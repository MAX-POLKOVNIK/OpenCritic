package com.opencritic.calendar.di

import com.opencritic.calendar.data.CalendarRepositoryImpl
import com.opencritic.calendar.domain.CalendarRepository
import com.opencritic.calendar.domain.GetGameCalendarInteractor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val calendarModule = module {
    single<CalendarRepository> { CalendarRepositoryImpl(get()) }

    singleOf(::GetGameCalendarInteractor)
}