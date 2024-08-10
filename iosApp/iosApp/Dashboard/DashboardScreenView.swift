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
    @EnvironmentObject private var router: IosRouter
    
    let viewModel: DashboardViewModel
    
    var body: some View {
        let _ = viewModel.setRouter(router: router)
        
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                DashboardStateContentView(state: content)
            }
        }
    }
}
