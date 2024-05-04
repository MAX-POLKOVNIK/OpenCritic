//
//  OutletReviewsScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct OutletReviewsScreenView: View {
    let viewModel: OutletReviewsViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            switch state {
            case let content as OutletReviewsStateContent:
                OutletReviewsStateContentView(state: content)
            case let loading as OutletReviewsStateLoading:
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
            case let error as OutletReviewsStateError:
                DashboardStateErrorView(state: DashboardStateError(error: error.message))
            default: fatalError("Unknown state")
            }
        }
    }
}
