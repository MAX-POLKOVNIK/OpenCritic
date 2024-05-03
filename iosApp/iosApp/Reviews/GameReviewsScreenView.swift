//
//  GameReviewsScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameReviewsScreenView: View {
    let viewModel: GameReviewsViewModel
    
    var body: some View {
        return FlowView(of: viewModel.state) { state in
            switch state {
            case let content as GameReviewsStateContent:
                GameReviewsStateContentView(state: content)
            case let loading as GameReviewsStateLoading:
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
            case let error as GameReviewsStateError:
                DashboardStateErrorView(state: DashboardStateError(error: error.message))
            default: fatalError("Unknown state")
            }
        }
    }
}
