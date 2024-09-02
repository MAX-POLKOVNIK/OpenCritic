//
//  YourGameListScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 05/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct YourGameListScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: GameListsRoute.InitArgs
    
    var body: some View {
        let viewModel: YourGameListViewModel = store.get(for: GameListsRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            YourGameListStateView(state: content)
        }
    }
}
