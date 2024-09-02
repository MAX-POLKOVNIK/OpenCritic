//
//  OutletReviewsScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Mvvm
import MvvmBase
import Navigation

struct OutletReviewsScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: OutletReviewsRoute.InitArgs
    
    var body: some View {
        let viewModel: OutletReviewsViewModel = store.get(for: OutletReviewsRoute.shared, args: args)

        RoutableView(viewModel: viewModel) {
            FlowView(of: viewModel) { state in
                switch state {
                case let content as OutletReviewsStateContent:
                    OutletReviewsStateContentView(state: content)
                case let loading as OutletReviewsStateLoading:
                    LoadingStateView(state: loading)
                case let error as OutletReviewsStateError:
                    ErrorStateView(state: error)
                default: fatalError("Unknown state")
                }
            }
        }
    }
}
