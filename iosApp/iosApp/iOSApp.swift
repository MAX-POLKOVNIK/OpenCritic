import SwiftUI
import shared
import Navigation
import About
import Auth
import Calendar
import Dashboard
import GameBrowser
import GameDetails
import GameList
import HallOfFame
import Main
import News
import Reviews
import Search

@main
struct iOSApp: App {
    init() {
        IosApp.shared.doInit(
            creators: [
                anyCreator(of: ArticleListScreenCreator()),
                anyCreator(of: DashboardScreenCreator()),
                anyCreator(of: SearchScreenCreator()),
                anyCreator(of: GameBrowserScreenCreator()),
                anyCreator(of: GameListsScreenCreator()),
                anyCreator(of: HallsOfFameScreenCreator()),
                anyCreator(of: AboutScreenCreator()),
                anyCreator(of: CalendarScreenCreator()),
                anyCreator(of: ArticleScreenCreator()),
                anyCreator(of: AuthScreenCreator()),
                anyCreator(of: GameListScreenCreator()),
                anyCreator(of: PeriodGameBrowserScreenCreator()),
                anyCreator(of: GameReviewsScreenCreator()),
                anyCreator(of: OutletReviewsScreenCreator()),
                anyCreator(of: AuthorReviewsScreenCreator()),
                anyCreator(of: GameDetailsScreenCreator()),
                anyCreator(of: GameMediaScreenCreator()),
            ]
        )
    }
	var body: some Scene {
		WindowGroup {
            MainScreenView()
		}
	}
}
