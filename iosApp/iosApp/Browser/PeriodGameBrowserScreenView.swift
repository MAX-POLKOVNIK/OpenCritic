//
//  PeriodGameBrowserScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PeriodGameBrowserScreenView: View {
    let viewModel: PeriodGameBrowserViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            switch state {
            case let content as PeriodGameBrowserStateContent:
                PeriodGameBrowserStateContentView(state: content)
            case let loading as PeriodGameBrowserStateLoading:
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
            case let error as PeriodGameBrowserStateError:
                DashboardStateErrorView(state: DashboardStateError(error: error.message))
            default: fatalError("Unknown state")
            }
        }
    }
}
