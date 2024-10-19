//
//  HallsOfFameScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct HallsOfFameScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: HallOfFameRoute.InitArgs
    
    var body: some View {
        let viewModel: HallsOfFameViewModel = store.get(for: HallOfFameRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            HallsOfFameContentView(content: content)
        }
    }
}
