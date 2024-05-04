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
    let viewModel: SearchViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            SearchStateView(state: state)
        }
    }
}
