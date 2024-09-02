//
//  DashboardScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct DashboardScreenView: View {
    @EnvironmentObject var store: ViewModelStore
    
    let args: DashboardRoute.InitArgs
    
    var body: some View {
        let viewModel: DashboardViewModel = store.get(for: DashboardRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            DashboardStateContentView(state: content)
        }
    }
}
