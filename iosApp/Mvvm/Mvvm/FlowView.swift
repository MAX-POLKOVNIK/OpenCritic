//
//  FlowView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Combine
import SwiftUI

public struct FlowView<State: ViewModelState, StateView>: View where StateView: View {
    @ObservedObject private var observableState: ObservableViewModelState<State>

    private let viewProducer: (State) -> StateView
    private let viewModel: BaseViewModel<State>

    public init(
        of vm: BaseViewModel<State>,
        @ViewBuilder viewProducer: @escaping (State) -> StateView
    ) {
        self.viewModel = vm
        self.observableState =
            ObservableViewModelState(publisher: FlowPublisher(vm.state), initial: vm.state.value)
        
        self.viewProducer = viewProducer
    }

    public var body: some View {
        viewProducer(self.observableState.value)
    }
}
