//
//  ArticleScreenView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct ArticleScreenView: View {
    @EnvironmentObject var store: ViewModelStore
    
    let args: ArticleRoute.InitArgs
    
    var body: some View {
        let viewModel: ArticleViewModel = store.get(for: ArticleRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            ArticleContentView(content: content)
        }
    }
}
