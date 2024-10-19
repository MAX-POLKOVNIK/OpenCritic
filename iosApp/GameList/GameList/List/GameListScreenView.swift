//
//  GameListScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct GameListScreenView: View {
    @EnvironmentObject var store: ViewModelStore
    
    let args: GameListRoute.InitArgs
    
    var body: some View {
        let viewModel: GameListViewModel = store.get(for: GameListRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            GameListContentView(content: content)
        }
    }
}
