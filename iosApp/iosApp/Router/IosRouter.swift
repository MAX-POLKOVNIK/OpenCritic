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

class IosRouter: ObservableObject, Router {
    @Published var path: NavigationPath = NavigationPath()
    
    @ViewBuilder func view(for route: Route) -> some View {
        if let route = route as? GameDetailsRoute {
            GameDetailsScreenView(gameId: route.gameId)
        } else {
            ContentView()
        }
    }
    
    func navigateBack() {
        path.removeLast()
    }
    
    func navigateTo(route: Route) {
        path.append(route)
    }
    
    func popToRoot() {
        path.removeLast(path.count)
    }
}
