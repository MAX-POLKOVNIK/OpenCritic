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
                viewModels[$0] = nil
            }
        }
    }
    
    private var viewModels: [Route: BaseViewModel<AnyObject>?] = [:]
    
    func viewModel<S: ViewModelState, T: BaseViewModel<S>>(for route: Route, arg: Any = Void()) -> T {
        if let viewModel = viewModels[route] {
            return viewModel as! T
        } else {
            let vm = koinViewModel(T.self, arg: arg)
            
            vm.setRouter(router: self)
            
            viewModels[route] = vm as! BaseViewModel<AnyObject>
            return vm
        }
    }
    
    @ViewBuilder func view(for route: Route) -> some View {
        switch route {
        case let gameRoute as GameDetailsRoute:
            GameDetailsScreenView(
                viewModel: viewModel(for: route, arg: gameRoute.gameId)
            )
        case let mediaRoute as GameMediaRoute:
            GameMediaScreenView(
                viewModel: viewModel(for: route, arg: mediaRoute.gameId)
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
