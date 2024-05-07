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
    @Published var path: [Route] = [] {
        willSet(newValue) {
            let routes = Set(path).subtracting(newValue)
            
            routes.forEach {
                print("removing vm for route: \($0)")
                viewModels[$0] = nil
            }
        }
    }
    
    private var viewModels: [Route: BaseViewModel<AnyObject>?] = [:]
    
    func viewModel<S: ViewModelState, T: BaseViewModel<S>>(for route: Route, args: [Any] = []) -> T {
        if let viewModel = viewModels[route] {
            print("returning cached vm for route: \(route)")
            return viewModel as! T
        } else {
            let vm = koinViewModel(T.self, args: args)
            
            vm.setRouter(router: self)
            
            print("adding vm for route: \(route)")
            viewModels[route] = vm as! BaseViewModel<AnyObject>
            return vm
        }
    }
    
    @ViewBuilder func view(for route: Route) -> some View {
        switch route {
        case let gameRoute as GameDetailsRoute:
            GameDetailsScreenView(
                viewModel: viewModel(for: route, args: [gameRoute.gameId, gameRoute.gameName])
            )
        case let mediaRoute as GameMediaRoute:
            GameMediaScreenView(
                viewModel: viewModel(for: route, args: [mediaRoute.gameId, mediaRoute.gameName])
            )
        case let reviewsRoute as GameReviewsRoute:
            GameReviewsScreenView(
                viewModel: viewModel(for: route, args: [reviewsRoute.gameId, reviewsRoute.gameName])
            )
        case let outletRoute as OutletReviewsRoute:
            OutletReviewsScreenView(
                viewModel: viewModel(for: route, args: [outletRoute.outletId, outletRoute.outletName])
            )
        case let authorRoute as AuthorReviewsRoute:
            AuthorReviewsScreenView(
                viewModel: viewModel(for: route, args: [authorRoute.authorId])
            )
        case let periodGameBrowserRoute as PeriodGameBrowserRoute:
            PeriodGameBrowserScreenView(
                viewModel: viewModel(for: route, args: [periodGameBrowserRoute.period])
            )
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

extension Array where Element: Hashable {
    func difference(from other: [Element]) -> [Element] {
        let thisSet = Set(self)
        let otherSet = Set(other)
        return Array(thisSet.symmetricDifference(otherSet))
    }
}
