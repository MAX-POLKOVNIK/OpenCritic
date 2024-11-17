//
//  ArticleHorizontalListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct ArticleHorizontalListItemView: View {
    let item: ArticleListItem
    
    var body: some View {
        Grid(horizontalSpacing: 0, verticalSpacing: 0) {
            GridRow {
                ForEach(0 ..< 10) { _ in
                    Color.clear
                }
            }
            .frame(height: 0)
            
            GridRow {
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
                .gridCellColumns(4) // 40 %
                
                VStack(alignment: .leading) {
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
                .padding(.leading)
                .gridCellColumns(6) // 60 %
            }
        }
        .contentShape(Rectangle())
        .onTapGesture {
            item.readMoreClick()
        }
    }
}

#Preview {
    ArticleHorizontalListItemView(
        item: ArticleListItemKt.ArticleListItem_PreviewData()
    )
}
