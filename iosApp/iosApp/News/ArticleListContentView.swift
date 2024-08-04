//
//  ArticleListContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleListContentView: View {
    let content: ArticleListContent
    
    var body: some View {
        List {
            ForEach(content.items, id: \.id) { item in
                ArticleListItemView(item: item)
                    .buttonStyle(BorderlessButtonStyle())
            }
            
            if content.isLoadingItemVisible {
                LoadingItemView(item: content.loadingItem)
                    .onAppear {
                        content.onLoadMore()
                    }
            }
        }
        .listStyle(.plain)
    }
}

#Preview {
    ArticleListContentView(
        content: ArticleListContentKt.ArticleListContent_PreviewData()
    )
}
