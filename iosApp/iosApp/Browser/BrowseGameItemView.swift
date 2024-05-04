//
//  BrowseGameItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct BrowseGameItemView: View {
    let item: BrowseGameItem
    
    var body: some View {
        ZStack(alignment: .bottomLeading) {
            CachedAsyncImage(
                url: URL(string: item.imageUrl),
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
                
            Rectangle()
                .foregroundColor(.clear)
                .background(
                    LinearGradient(
                        gradient: Gradient(
                            colors: [.clear, .black]
                        ),
                        startPoint: .top,
                        endPoint: .bottom
                    )
                )
                .aspectRatio(16 / 9, contentMode: .fit)
            
            VStack(alignment: .leading) {
                HStack(alignment: .center) {
                    if item.isTierVisible {
                        Image(item.tierImageResource)
                            .resizable()
                            .frame(width: 56, height: 56)
                    }
                    
                    if item.isTopCriticVisibleIndicator {
                        RankCircleIndicatorView(item: item.topCriticScoreIndicator)
                            .frame(width: 56, height: 56)
                    }
                    
                    if item.isPercentRecommendedIndicatorVisible {
                        RankCircleIndicatorView(item: item.percentRecommendedIndicator)
                            .frame(width: 56, height: 56)
                    }
                }
                
                Text(item.nameText)
                    .bold()
                    .foregroundStyle(.white)
                
                HStack {
                    Image(systemName: item.dateImageResource)
                        .foregroundColor(.gray)
                    
                    Text(item.dateText)
                        .foregroundStyle(.gray)
                }
            }
            .padding()
        }
        .onTapGesture { item.onClick() }
    }
}

#Preview {
    BrowseGameItemView(
        item: BrowseGameItemKt.BrowseGameItem_PreviewData(
            imageResourceProvider: IosImagesResourceProvider()
        )
    )
}
