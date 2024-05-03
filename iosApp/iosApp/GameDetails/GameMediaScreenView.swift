//
//  GameMediaScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameMediaScreenView: View {
    @EnvironmentObject var router: IosRouter
    
    private let gameId: Int64
    private let viewModel: GameMediaViewModel
    
    init(gameId: Int64) {
        self.gameId = gameId
        viewModel = koinViewModel(GameMediaViewModel.self, arg: gameId)
    }
    
    var body: some View {
        viewModel.setRouter(router: router)
        
        return FlowView(of: viewModel.state) { state in
            switch state {
            case let content as GameMediaStateContent:
                GameMediaStateContentView(state: content)
            case let loading as GameMediaStateLoading:
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
            case let error as GameMediaStateError:
                DashboardStateErrorView(state: DashboardStateError(error: error.message))
            default: fatalError("Unknown state")
            }
        }
    }
}
