//
//  RoutableView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

public struct RoutableView<State: ViewModelState, Content: View>: View {
    @EnvironmentObject private var router: IosRouter
    
    private let content: () -> Content
    private let viewModel: BaseViewModel<State>
    
    public init(viewModel: BaseViewModel<State>, @ViewBuilder content: @escaping () -> Content) {
        self.content = content
        self.viewModel = viewModel
    }
    
    public var body: some View {
        viewModel.setRouter(router: router)
        
        return content()
    }
}

