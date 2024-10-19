//
//  MainScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Mvvm
import MvvmBase

public struct MainScreenView: View {
    private let viewModel: MainViewModel = koinViewModel(MainViewModel.self)
    
    public init() {}
    
    public var body: some View {
        FlowView(of: viewModel) { state in
            CommonStateView(state: state) { content in
                MainStateView(state: content)
            }
        }
        .environmentObject(ViewModelStore())
    }
}
