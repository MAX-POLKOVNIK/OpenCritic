//
//  CommonScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/07/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CommonScreenView<Content: AnyObject, ContentView>: View where ContentView: View {
    let state: CommonViewModelState<Content>
    @ViewBuilder
    let viewProducer: (Content) -> ContentView
    
    var body: some View {
        if let fullScreenError = state.fullScreenError {
            ErrorStateView(state: fullScreenError)
        }
        
        if let fullScreenLoading = state.fullScreenLoading {
            LoadingStateView(state: fullScreenLoading)
        }
        
        if let content = state.content {
            viewProducer(content)
        }
    }
}
