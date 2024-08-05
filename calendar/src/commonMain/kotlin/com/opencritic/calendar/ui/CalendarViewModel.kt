package com.opencritic.calendar.ui

import com.opencritic.calendar.domain.CalendarGame
import com.opencritic.calendar.domain.GetGameCalendarInteractor
import com.opencritic.mvvm.BaseContentViewModel
import com.opencritic.mvvm.CommonViewModelState
import com.opencritic.navigation.GameDetailsRoute
import com.opencritic.resources.text.asTextSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class CalendarViewModel(
    private val getGameCalendarInteractor: GetGameCalendarInteractor,
) : BaseContentViewModel<CalendarContent>() {
    override fun initialState(): CommonViewModelState<CalendarContent> =
        CommonViewModelState.loading(title = "Gaming Calendar".asTextSource())

    override fun onStateInit() {
        super.onStateInit()

        loadCalendar()
    }

    private fun loadCalendar() {
        scope.launch {
            showLoading()

            getGameCalendarInteractor()
                .onFailure {
                    showError(it) {
                        loadCalendar()
                    }
                }
                .onSuccess { calendar ->
                    setContent {
                        CalendarContent(
                            description = "See all of the top releases in gaming for the upcoming season. Games are evaluated for placement on the calendar on a case-by-case basis. If you think we're missing a game, email us at factcheck@opencritic.com.".asTextSource(),
                            cards = calendar.months.map {
                                CalendarGameMonthCard(
                                    month = it,
                                    onPosterClick = ::navigateToGame
                                )
                            },
                        )
                    }

                    startTimer()
                }
        }
    }

    private fun startTimer() {
        scope.launch {
            while (true) {
                delay(3.seconds)

                updateContentIfSet {
                    copy(
                        cards = cards.map { it.switchedNext() }
                    )
                }
            }
        }
    }

    private fun navigateToGame(calendarGame: CalendarGame) {
        requireRouter()
            .navigateTo(
                GameDetailsRoute(
                    gameId = calendarGame.id,
                    gameName = calendarGame.name,
                )
            )
    }
}