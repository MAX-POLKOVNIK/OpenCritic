//
//  MainScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MainScreenView: View {
    private let viewModel: MainViewModel = koinViewModel(MainViewModel.self)
    
    var body: some View {
        return FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                MainStateView(state: content)
            }
        }
    }
}
