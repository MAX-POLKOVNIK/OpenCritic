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
    func get(stringResource: String) -> String {
        NSLocalizedString(stringResource, comment: stringResource)
    }
    
    var popularGames: String { "str_popular_games" }
    var popularGamesDescription: String { "str_popular_games_description" }
    
    var tabBrowse: String { "str_tab_browse" }
    var tabCalendar: String { "str_tab_calendar" }
    var tabMain: String { "str_tab_main" }
    var tabSearch: String { "str_tab_search" }
    var tabYourList: String { "str_tab_your_list" }
}
