//
//  CommonScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03.09.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Navigation

public struct CommonScreenView<Content: AnyObject, ViewModel: BaseContentViewModel<Content>, ContentView: View>: View {
    private let viewModel: ViewModel
    
    @ViewBuilder
    private let content: (Content) -> ContentView
    
    public init(of viewModel: ViewModel, content: @escaping (Content) -> ContentView) {
        self.viewModel = viewModel
        self.content = content
    }
    
    public var body: some View {
        RoutableView(viewModel: viewModel) {
            FlowView(of: viewModel) { state in
                CommonStateView(state: state) { content in
                    self.content(content)
                }
            }
        }
    }
}
