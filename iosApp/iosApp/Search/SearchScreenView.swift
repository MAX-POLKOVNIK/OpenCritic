//
//  SearchScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SearchScreenView: View {
    @EnvironmentObject private var router: IosRouter
    
    let viewModel: SearchViewModel
    
    var body: some View {
        let _ = viewModel.setRouter(router: router)
        
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                SearchStateView(state: content)
            }
        }
    }
}
