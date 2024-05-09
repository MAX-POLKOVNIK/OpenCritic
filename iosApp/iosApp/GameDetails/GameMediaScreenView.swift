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
    let viewModel: GameMediaViewModel
    
    var body: some View {
        return FlowView(of: viewModel.state) { state in
            switch state {
            case let content as GameMediaStateContent:
                GameMediaStateContentView(state: content)
            case let loading as GameMediaStateLoading:
                LoadingStateView(state: loading)
            case let error as GameMediaStateError:
                ErrorStateView(state: error)
            default: fatalError("Unknown state")
            }
        }
    }
}
