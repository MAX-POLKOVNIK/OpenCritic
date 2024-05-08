//
//  AuthorReviewsScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AuthorReviewsScreenView: View {
    let viewModel: AuthorReviewsViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            switch state {
            case let content as AuthorReviewsStateContent:
                AuthorReviewsStateContentView(state: content)
            case let loading as AuthorReviewsStateLoading:
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
            case let error as AuthorReviewsStateError:
                DashboardStateErrorView(state: DashboardStateError(error: error.message))
            default: fatalError("Unknown state")
            }
        }
    }
}
