//
//  IosStringResourceProvider.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class IosStringProvider : StringProvider {
    var gameActionFavorite: String { string("str_game_action_favorite") }
    var gameActionPlayed: String { string("str_game_action_played") }
    var gameActionWant: String { string("str_game_action_want") }
    
    var criticsRecommend: String { string("str_critics_recommend") }
    var openCriticRating: String { string("str_open_critic_rating") }
    var topCriticAverage: String { string("str_top_critic_average") }
    
    var fair: String { string("str_fair") }
    var fairDescription: String { string("str_fair_description") }
    
    var mighty: String { string("str_mighty") }
    var mightyDescription: String { string("str_mighty_description") }
    
    var strong: String { string("str_strong") }
    var strongDescription: String { string("str_strong_description") }
    
    var weak: String { string("str_weak") }
    var weakDescription: String { string("str_weak_description") }
    
    var whoIsMightyMan: String { string("who_is_mighty_man") }
    var whoIsMightyManColorDescription: String { string("who_is_mighty_man_color_description") }
    var whoIsMightyManDescription: String { string("who_is_mighty_man_description") }
    
    func buyNowOnFormatted(name: String) -> String { string("str_buy_now_on", args: [name]) }
    
    var popularGames: String { string("str_popular_games") }
    var popularGamesDescription: String { string("str_popular_games_description") }
    
    var featuredDeals: String { string("str_featured_deals") }
    var featuredDealsDescription: String { string("str_featured_deals_description") }
    
    var recentlyReleased: String { string("str_recently_released") }
    var reviewedToday: String { string("str_reviewed_today") }
    var upcomingReleases: String { string("str_upcoming_releases") }
    
    var viewMore: String { string("str_view_more") }
    var reviewedThisWeek: String { string("str_reviewed_this_week") }
    
    func hallOfFameFormatted(year: String) -> String { string("str_hall_of_fame", args: [year]) }
    func hallOfFameDescriptionFormatted(year: String) -> String { string("str_hall_of_fame_description", args: [year]) }
    
    var tabBrowse: String { string("str_tab_browse") }
    var tabCalendar: String { string("str_tab_calendar") }
    var tabMain: String { string("str_tab_main") }
    var tabSearch: String { string("str_tab_search") }
    var tabYourList: String { string("str_tab_your_list") }
    
    var viewAll: String { string("str_view_all") }
    func viewAllReviewsFormatted(count: String) -> String { string("str_view_all_reviews", args: [count]) }
    func criticReviewsForFormatted(name: String) -> String { string("str_critic_reviews_for_formatted", args: [name]) }
    var readFullReview: String { string("str_read_full_review") }
    
    var media: String { string("str_media") }
    var screenshots: String { string("str_screenshots") }
    var trailers: String { string("str_trailers") }
    
    var critic: String { string("str_critic") }
    var game: String { string("str_game") }
    var outlet: String { string("str_outlet") }
    var searchHint: String { string("str_search_hint") }
    
    func gameScreenshotsAndTrailers(name: String) -> String { string("str_game_screenshots_and_trailers", args: [name]) }
    
    func gameReviewRankedDescription(name: String, percent: String) -> String { string("str_game_review_ranked_description", args: [name, percent]) }
    
    var sort: String { string("str_sort") }
    var sortDefault: String { string("str_sort_default") }
    var sortMostPopular: String { string("str_sort_most_popular") }
    var sortNewestFirst: String { string("str_sort_newest") }
    var sortOldestFirst: String { string("str_sort_oldest") }
    var sortScoreHighestToLowest: String { string("str_sort_highest") }
    var sortScoreLowestToHighest: String { string("str_sort_lowest") }
    
    private func string(_ stringResource: String, args: [String] = []) -> String {
        String(format: NSLocalizedString(stringResource, comment: stringResource), args.map { $0 as CVarArg })
    }
}
