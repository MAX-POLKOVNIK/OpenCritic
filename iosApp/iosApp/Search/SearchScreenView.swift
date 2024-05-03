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
    @EnvironmentObject var router: IosRouter
    
    private let viewModel: SearchViewModel = koinViewModel(SearchViewModel.self)
    
    var body: some View {
        viewModel.setRouter(router: router)
        
        return FlowView(of: viewModel.state) { state in
            SearchStateView(state: state)
        }
    }
}
