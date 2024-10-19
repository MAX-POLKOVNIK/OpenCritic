//
//  PeriodGameBrowserScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct PeriodGameBrowserScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: PeriodGameBrowserRoute.InitArgs
    
    var body: some View {
        let viewModel: PeriodGameBrowserViewModel = store.get(for: PeriodGameBrowserRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            PeriodGameBrowserStateContentView(state: content)
        }
    }
}
