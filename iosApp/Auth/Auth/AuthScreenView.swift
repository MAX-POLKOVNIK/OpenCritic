//
//  AuthScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct AuthScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: AuthRoute.InitArgs
    
    var body: some View {
        let viewModel: AuthViewModel = store.get(for: AuthRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            AuthContentView(content: content)
        }
    }
}
