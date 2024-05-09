//
//  GameBrowserScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameBrowserScreenView: View {
    let viewModel: GameBrowserViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            switch state {
            case let content as GameBrowserStateContent:
                GameBrowserStateContentView(state: content)
            case let loading as GameBrowserStateLoading:
                LoadingStateView(state: loading)
            case let error as GameBrowserStateError:
                ErrorStateView(state: error)
            default: fatalError("Unknown state")
            }
        }
    }
}
