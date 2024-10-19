//
//  ArticleListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleListItemView: View {
    let item: ArticleListItem
    
    var body: some View {
        VStack(alignment: .leading) {
            CachedAsyncImage(
                url: URL(string: item.bannerImageUrl),
                urlCache: .imageCache
            ) { image in
                image.resizable()
                    .scaledToFill()
                    .aspectRatio(16 / 9, contentMode: .fit)
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .clipped()
            } placeholder: {
                Rectangle()
                    .foregroundColor(.gray)
                    .background(.gray)
                    .aspectRatio(16 / 9, contentMode: .fit)
            }
            .card()
            
            Text(item.title)
                .font(.title)
            
            Text(item.summary)
                .padding(.vertical)
            
            if item.isOutletVisible {
                HStack {
                    Text(item.outletTitleText)
                    Button(item.outletText) {
                        item.onOutletClick()
                    }
                }
            }
            
            Text(item.writtenBy)
            Text(item.publishedDateText)
            
            HStack {
                Spacer()
                Button(item.readMoreText) {
                    item.onReadMoreClick()
                }
            }
        }
    }
}

#Preview {
    ArticleListItemView(
        item: ArticleListItemKt.ArticleListItem_PreviewData()
    )
}
