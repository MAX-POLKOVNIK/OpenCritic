//
//  GameDetailsCompactView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameDetailsCompactView: View {
    let state: GameDetailsContent
    
    var body: some View {
        LazyVStack {
            if state.isSquareImageVisible {
                CachedAsyncImage(
                    url: URL(string: state.squareImageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                        .scaledToFill()
                } placeholder: {
                    NoGamePosterView()
                }
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .aspectRatio(1.0, contentMode: .fit)
            }
            
            YourGameIndicatorItemView(
                item: state.yourGameIndicatorItem
            ).padding()
            
            VStack(
                alignment: .leading
            ) {
                Text(state.name)
                    .font(.title)
                    .bold()
                    .padding()
            
                Text(state.companiesText)
                    .padding(.horizontal)
                Text(state.releaseDateText.text())
                    .padding(.horizontal)
                Text(state.platformsText)
                    .padding(.horizontal)
                    .bold()
                
                Spacer()
                    .frame(height: 16)
                
                if state.isTierVisible {
                    HStack(spacing: 16) {
                        Image(state.tierImageResource)
                            .resizable()
                            .scaledToFill()
                            .aspectRatio(1.0, contentMode: .fit)
                            .frame(
                                minWidth: /*@START_MENU_TOKEN@*/0/*@END_MENU_TOKEN@*/,
                                maxWidth: .infinity
                            )
                        
                        RankCircleIndicatorView(item: state.topCriticScore)
                            .frame(minWidth: 0, maxWidth: .infinity)
                        
                        RankCircleIndicatorView(item: state.recommendedPercent)
                            .frame(minWidth: 0, maxWidth: .infinity)
                    }
                        .padding(.horizontal)
                    
                    HStack(spacing: 16) {
                        Text(state.tierDescription)
                            .multilineTextAlignment(.center)
                            .frame(minWidth: 0, maxWidth: .infinity)
                        
                        Text(state.topCriticScoreDescription)
                            .multilineTextAlignment(.center)
                            .frame(minWidth: 0, maxWidth: .infinity)
                        
                        Text(state.criticsRecommendDescription)
                            .multilineTextAlignment(.center)
                            .frame(minWidth: 0, maxWidth: .infinity)
                    }
                    .padding(.horizontal)
                }
                
                Spacer()
                    .frame(height: 16)
                
                ForEach(state.briefReviews, id: \.self) { item in
                    ReviewBriefListItemView(item: item)
                }
                
                if state.isViewAllVisible {
                    HStack {
                        Spacer()
                        Button(state.viewAllText) { state.onViewAllReviewsClick() }
                            .padding()
                    }
                }
            }
                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                .card()
                .padding(.horizontal)
        }
    }
}

#Preview {
    GameDetailsCompactView(
        state: GameDetailsContentKt.GameDetailsContent_PreviewData()
    )
}
