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
            CommonScreenView(state: state) { content in
                GameBrowserStateContentView(state: content)
            }
        }
    }
}
