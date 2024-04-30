//
//  DashboardScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardScreenView: View {
    private let viewModel: DashboardViewModel = koinViewModel(DashboardViewModel.self)
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            switch state {
            case let content as DashboardStateContent:
                DashboardStateContentView(state: content)
            case let loading as DashboardStateLoading:
                DashboardStateLoadingView(state: loading)
            case let error as DashboardStateError:
                DashboardStateErrorView(state: error)
            default: fatalError("Unknown state")
            }
        }
    }
}

//#Preview {
//    DashboardScreenView()
//}
