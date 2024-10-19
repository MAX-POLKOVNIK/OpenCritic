//
//  GameMediaScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Mvvm
import MvvmBase
import Navigation

struct GameMediaScreenView: View {
    @EnvironmentObject var store: ViewModelStore
    
    let args: GameMediaRoute.InitArgs
    
    var body: some View {
        let viewModel: GameMediaViewModel = store.get(for: GameMediaRoute.shared, args: args)
        
        RoutableView(viewModel: viewModel) {
            FlowView(of: viewModel) { state in
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
}
