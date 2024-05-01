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
    
    var tabBrowse: String { "str_tab_browse" }
    var tabCalendar: String { "str_tab_calendar" }
    var tabMain: String { "str_tab_main" }
    var tabSearch: String { "str_tab_search" }
    var tabYourList: String { "str_tab_your_list" }
}
