//
//  GameDetailsScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct GameDetailsScreenView: View {
    @EnvironmentObject var store: ViewModelStore

    let args: GameDetailsRoute.InitArgs
    
    var body: some View {
        let viewModel: GameDetailsViewModel = store.get(for: GameDetailsRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            GameDetailsContentView(state: content)
        }
    }
}
