//
//  SearchScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct SearchScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: SearchRoute.InitArgs
    
    var body: some View {
        let viewModel: SearchViewModel = store.get(for: SearchRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            SearchStateView(state: content)
        }
    }
}
