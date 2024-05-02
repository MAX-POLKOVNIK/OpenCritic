//
//  GameDetailsScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameDetailsScreenView: View {
    @EnvironmentObject var router: IosRouter
    
    private let gameId: Int64
    private let viewModel: GameDetailsViewModel
    
    init(gameId: Int64) {
        self.gameId = gameId
        viewModel = koinViewModel(GameDetailsViewModel.self, arg: gameId)
    }
    
    var body: some View {
        viewModel.setRouter(router: router)
        
        return FlowView(of: viewModel.state) { state in
            switch state {
            case let content as GameDetailsStateContent:
                GameDetailsStateContentView(state: content)
            case let loading as GameDetailsStateLoading:
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
            case let error as GameDetailsStateError:
                DashboardStateErrorView(state: DashboardStateError(error: error.message))
            default: fatalError("Unknown state")
            }
        }
    }
}
