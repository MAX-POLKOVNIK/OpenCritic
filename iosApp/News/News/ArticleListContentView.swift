//
//  ArticleListContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Mvvm
import Views

struct ArticleListContentView: View {
    let content: ArticleListContent
    
    var body: some View {
        List {
            ForEach(content.itemIndices, id: \.self) { index in
                LazyVStack {
                    if let item = content.getItemAt(index) {
                        ArticleListItemView(item: item)
                            .buttonStyle(BorderlessButtonStyle())
                    }
                }
            }
        }
        .listStyle(.plain)
        .refreshable {
            let _ = try? await content.onRefresh.invoke()
        }
    }
}

#Preview {
    ArticleListContentView(
        content: ArticleListContentKt.ArticleListContent_PreviewData()
    )
}
