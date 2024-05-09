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
            switch state {
            case let webview as AuthStateWebView:
                AuthStateWebViewView(state: webview)
            case let methodList as AuthStateMethodList:
                AuthStateMethodListView(state: methodList)
            case let error as AuthStateError:
                ErrorStateView(state: error)
            case let loading as AuthStateLoading:
                LoadingStateView(state: loading)
            default: fatalError("Unknown state")
            }
        }
    }
}

//#Preview {
//    AuthScreenView()
//}
