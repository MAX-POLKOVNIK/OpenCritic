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
    let viewModel: GameDetailsViewModel
    
    var body: some View {
        return FlowView(of: viewModel.state) { state in
            switch state {
            case let content as GameDetailsStateContent:
                GameDetailsStateContentView(state: content)
            case let loading as GameDetailsStateLoading:
                LoadingStateView(state: loading)
            case let error as GameDetailsStateError:
                ErrorStateView(state: error)
            default: fatalError("Unknown state: \(state)")
            }
        }
    }
}
