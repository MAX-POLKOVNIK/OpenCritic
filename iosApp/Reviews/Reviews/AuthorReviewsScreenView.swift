//
//  AuthorReviewsScreenView.swift
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

struct AuthorReviewsScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: AuthorReviewsRoute.InitArgs
    
    var body: some View {
        let viewModel: AuthorReviewsViewModel = store.get(for: AuthorReviewsRoute.shared, args: args)
        
        RoutableView(viewModel: viewModel) {
            FlowView(of: viewModel) { state in
                switch state {
                case let content as AuthorReviewsStateContent:
                    AuthorReviewsStateContentView(state: content)
                case let loading as AuthorReviewsStateLoading:
                    LoadingStateView(state: loading)
                case let error as AuthorReviewsStateError:
                    ErrorStateView(state: error)
                default: fatalError("Unknown state")
                }
            }
        }
    }
}
