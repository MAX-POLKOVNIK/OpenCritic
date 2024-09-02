//
//  GameBrowserScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct GameBrowserScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: GameBrowserRoute.InitArgs
    
    var body: some View {
        let viewModel: GameBrowserViewModel = store.get(for: GameBrowserRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            GameBrowserStateContentView(state: content)
        }
    }
}
