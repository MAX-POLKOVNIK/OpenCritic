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
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                GameDetailsContentView(state: content)
            }
        }
    }
}
