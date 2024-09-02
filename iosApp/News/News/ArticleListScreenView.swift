//
//  ArticleListScreen.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import MvvmBase
import Mvvm

struct ArticleListScreenView: View {
    @EnvironmentObject private var store: ViewModelStore
    
    let args: ArticleListRoute.InitArgs
    
    var body: some View {
        let viewModel: ArticleListViewModel = store.get(for: ArticleListRoute.shared, args: args)
        
        CommonScreenView(of: viewModel) { content in
            ArticleListContentView(content: content)
        }
    }
}
