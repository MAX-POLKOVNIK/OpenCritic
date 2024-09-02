//
//  CalendarScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct CalendarScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: CalendarRoute.InitArgs
    
    var body: some View {
        let viewModel: CalendarViewModel = store.get(for: CalendarRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            CalendarContentView(content: content)
        }
    }
}
