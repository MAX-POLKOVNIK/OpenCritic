//
//  ObservableViewModelState.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

class ObservableViewModelState<T : ViewModelState>: ObservableObject {
    @Published var value: T

    init(publisher: AnyPublisher<T, Never>, initial: T) {
        value = initial
        
        publisher
            .compactMap { $0 }
            .receive(on: DispatchQueue.main)
            .assign(to: &$value)
    }
}
