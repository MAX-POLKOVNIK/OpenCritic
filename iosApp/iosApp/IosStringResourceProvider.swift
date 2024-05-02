//
//  IosStringResourceProvider.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class IosStringResourceProvider : StringResourceProvider {
    func get(stringResource: String, args: [String]) -> String {
        String(format: NSLocalizedString(stringResource, comment: stringResource), args.map { $0 as CVarArg })
    }
    
    var gameActionFavorite: String { "str_game_action_favorite" }
    var gameActionPlayed: String { "str_game_action_played" }
    var gameActionWant: String { "str_game_action_want" }
    
    var criticsRecommend: String { "str_critics_recommend" }
    var openCriticRating: String { "str_open_critic_rating" }
    var topCriticAverage: String { "str_top_critic_average" }
    
    var fair: String { "str_fair" }
    var fairDescription: String { "str_fair_description" }
    
    var mighty: String { "str_mighty" }
    var mightyDescription: String { "str_mighty_description" }
    
    var strong: String { "str_strong" }
    var strongDescription: String { "str_strong_description" }
    
    var weak: String { "str_weak" }
    var weakDescription: String { "str_weak_description" }
    
    var whoIsMightyMan: String { "who_is_mighty_man" }
    var whoIsMightyManColorDescription: String { "who_is_mighty_man_color_description" }
    var whoIsMightyManDescription: String { "who_is_mighty_man_description" }
    
    var buyNowOnFormatted: String { "str_buy_now_on" }
    
    var popularGames: String { "str_popular_games" }
    var popularGamesDescription: String { "str_popular_games_description" }
    
    var featuredDeals: String { "str_featured_deals" }
    var featuredDealsDescription: String { "str_featured_deals_description" }
    
    var recentlyReleased: String { "str_recently_released" }
    var reviewedToday: String { "str_reviewed_today" }
    var upcomingReleases: String { "str_upcoming_releases" }
    
    var viewMore: String { "str_view_more" }
    var reviewedThisWeek: String { "str_reviewed_this_week" }
    
    var hallOfFameFormatted: String { "str_hall_of_fame" }
    var hallOfFameDescriptionFormatted: String { "str_hall_of_fame_description" }
    
    var tabBrowse: String { "str_tab_browse" }
    var tabCalendar: String { "str_tab_calendar" }
    var tabMain: String { "str_tab_main" }
    var tabSearch: String { "str_tab_search" }
    var tabYourList: String { "str_tab_your_list" }
    
    var viewAll: String { "str_view_all" }
    var viewAllReviewsFormatted: String { "str_view_all_reviews" }
    
    var media: String { "str_media" }
    var screenshots: String { "str_screenshots" }
    var trailers: String { "str_trailers" }
}
