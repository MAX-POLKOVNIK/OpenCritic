//
//  IosRouter.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import UIKit

class IosRouter: ObservableObject, Router {
    @Published var path: NavigationPath = NavigationPath()
    
    @ViewBuilder func view(for route: Route) -> some View {
        switch route {
        case let gameRoute as GameDetailsRoute:
            GameDetailsScreenView(gameId: gameRoute.gameId)
        case let mediaRoute as GameMediaRoute:
            GameMediaScreenView(gameId: mediaRoute.gameId)
        default:
            ContentView()
        }
    }
    
    func navigateBack() {
        path.removeLast()
    }
    
    func navigateTo(route: Route) {
        if let urlRoute = route as? UrlRoute, let url = URL(string: urlRoute.url) {
            UIApplication.shared.open(url)
            
            return
        }
        
        path.append(route)
    }
    
    func popToRoot() {
        path.removeLast(path.count)
    }
}
