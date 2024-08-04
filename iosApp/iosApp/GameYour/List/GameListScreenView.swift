//
//  GameListScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameListScreenView: View {
    let viewModel: GameListViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                GameListContentView(content: content)
            }
        }
    }
}
