//
//  AuthScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct AuthScreenView: View {
    let viewModel: AuthViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                AuthContentView(content: content)
            }
        }
    }
}
