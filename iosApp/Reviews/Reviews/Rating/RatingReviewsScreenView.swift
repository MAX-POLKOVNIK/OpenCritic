//
//  RatingReviewsScreenView.swift
//  Reviews
//
//  Created by Max Polkovnik on 19/11/2024.
//


import SwiftUI
import shared
import Mvvm
import MvvmBase
import ScreenNavigation
struct RatingReviewsScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: GameReviewsRoute.InitArgs
    
    var body: some View {
        let viewModel: GameReviewsViewModel = store.get(for: GameReviewsRoute.shared, args: args)
        
        RoutableView(viewModel: viewModel) {
            FlowView(of: viewModel) { state in
                switch state {
                case let content as GameReviewsStateContent:
                    GameReviewsStateContentView(state: content)
                case let loading as GameReviewsStateLoading:
                    LoadingStateView(state: loading)
                case let error as GameReviewsStateError:
                    ErrorStateView(state: error)
                default: fatalError("Unknown state")
                }
            }
        }
    }
}
