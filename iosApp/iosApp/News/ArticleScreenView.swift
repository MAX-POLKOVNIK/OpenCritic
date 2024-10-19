//
//  ArticleScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleScreenView: View {
    let viewModel: ArticleViewModel
    
    var body: some View {
        FlowView(of: viewModel.state) { state in
            CommonScreenView(state: state) { content in
                ArticleContentView(content: content)
            }
        }
    }
}
