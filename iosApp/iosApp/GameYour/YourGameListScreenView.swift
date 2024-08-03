//
//  YourGameListScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 05/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YourGameListScreenView: View {
    let viewModel: YourGameListViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                YourGameListStateView(state: content)
            }
        }
    }
}
