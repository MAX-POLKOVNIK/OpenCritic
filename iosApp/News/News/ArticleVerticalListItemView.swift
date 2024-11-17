//
//  ArticleListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct ArticleVerticalListItemView: View {
    let item: ArticleListItem
    
    var body: some View {
        VStack(alignment: .leading) {
            LazyImage(url: URL(string: item.bannerImageUrl)) { state in
                if let image = state.image {
                    image.resizable()
                        .scaledToFill()
                        .aspectRatio(16 / 9, contentMode: .fit)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .clipped()
                } else {
                    Rectangle()
                        .foregroundColor(.gray)
                        .background(.gray)
                        .aspectRatio(16 / 9, contentMode: .fit)
                }
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
                        item.outletClick()
                    }
                }
            }
            
            Text(item.writtenBy)
            Text(item.publishedDateText)
            
            HStack {
                Spacer()
                Button(item.readMoreText) {
                    item.readMoreClick()
                }
            }
        }
        .onTapGesture {
            item.readMoreClick()
        }
    }
}

#Preview {
    ArticleVerticalListItemView(
        item: ArticleListItemKt.ArticleListItem_PreviewData()
    )
}
