//
//  GameDetailsRegularView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameDetailsRegularView: View {
    let state: GameDetailsContent
    
    var body: some View {
        LazyVStack {
            ZStack(alignment: .bottomTrailing) {
                CachedAsyncImage(
                    url: URL(string: state.bannerImageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.centerCropped()
                        
                } placeholder: {
                    NoGamePosterView()
                }
                .frame(minWidth: 0, maxWidth: .infinity)
                .aspectRatio(16 / 9, contentMode: .fit)
                
                YourGameIndicatorItemView(
                    item: state.yourGameIndicatorItem
                )
                .frame(width: 300, height: 50, alignment: .bottomTrailing)
                .padding()
            }
            
            VStack(alignment: .leading) {
                Text(state.name)
                    .font(.title)
                    .bold()
                    .padding()
                
                HStack {
                    VStack(
                        alignment: .leading
                    ) {
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
                            HStack(spacing: 32) {
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
                            
                            HStack(spacing: 32) {
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
                        
                        
                    }
                    
                    VStack {
                        ForEach(state.briefReviews, id: \.self) { item in
                            ReviewBriefListItemView(item: item)
                        }
                        
                        Spacer()
                        
                        if state.isViewAllVisible {
                            HStack {
                                Spacer()
                                Button(state.viewAllText) { state.onViewAllReviewsClick() }
                                    .padding(.horizontal)
                            }
                        }
                    }
                    .padding()
                }
            }
            
            
            .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            .card()
            .padding()
        }
    }
}

#Preview {
    GameDetailsRegularView(
        state: GameDetailsContentKt.GameDetailsContent_PreviewData()
    )
}

extension Image {
    func centerCropped() -> some View {
        GeometryReader { geo in
            self
            .resizable()
            .scaledToFill()
            .frame(width: geo.size.width, height: geo.size.height)
            .clipped()
        }
    }
}

