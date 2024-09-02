//
//  AboutScreen.swift
//  iosApp
//
//  Created by Max Polkovnik on 06/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct AboutScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: AboutRoute.InitArgs
    
    var body: some View {
        let viewModel: AboutViewModel = store.get(for: AboutRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            AboutContentView(content: content)
        }
    }
}
