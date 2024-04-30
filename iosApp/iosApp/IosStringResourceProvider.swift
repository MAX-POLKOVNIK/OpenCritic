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
}
