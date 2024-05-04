//
//  IosImagesResourceProvider.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class IosImagesResourceProvider : ImageResourceProvider {
    var gameActionFavorite: String { "heart.fill" }
    var gameActionPlayed: String { "checkmark" }
    var gameActionWant: String { "plus" }
    
    var fairMan: String { "fair-man" }
    var mightyMan: String { "mighty-man" }
    var strongMan: String { "strong-man" }
    var weakMan: String { "weak-man" }
    
    var fairHead: String { "fair-head" }
    var mightyHead: String { "mighty-head" }
    var strongHead: String { "strong-head" }
    var weakHead: String { "weak-head" }
    
    var tabBrowse: String { "list.dash.header.rectangle" }
    var tabCalendar: String { "calendar" }
    var tabMain: String { "newspaper" }
    var tabSearch: String { "magnifyingglass" }
    var tabYourList: String { "star" }
    
    var bullseye: String { "circle.circle" }
    var chartPie: String { "chart.pie.fill" }
    var hashTag: String { "number" }
    var thumbUp: String { "hand.thumbsup.fill" }
    
    var home: String { "house.fill" }
    var playstation: String { "playstation.logo" }
    var xbox: String { "xbox.logo" }
}
