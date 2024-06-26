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

struct FlowView<State: ViewModelState, StateView>: View where StateView: View {

    @ObservedObject private var observableState: ObservableViewModelState<State>

    private let viewProducer: (State) -> StateView

    public init(
        of flow: ViewModelStateFlow<State>,
        @ViewBuilder viewProducer: @escaping (State) -> StateView
    ) {
        self.observableState =
            ObservableViewModelState(publisher: FlowPublisher(flow), initial: flow.value)
        
        self.viewProducer = viewProducer
    }

    public var body: some View {
        viewProducer(self.observableState.value)
    }
}
