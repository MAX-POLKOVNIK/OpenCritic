//
//  GameDetailsStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameDetailsContentView: View {
    let state: GameDetailsContent
    
    var body: some View {
        ScrollView(.vertical) {
            LazyVStack(alignment: .leading) {
                if state.isImageVisible {
                    CachedAsyncImage(
                        url: URL(string: state.imageUrl),
                        urlCache: .imageCache
                    ) { image in
                        image.resizable()
                            .scaledToFill()
                    } placeholder: {
                        Color.gray
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
                
                if state.isMediaVisible {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(state.mediaText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    ForEach(state.media, id: \.self) { m in
                        switch m {
                        case let trailer as TrailerItem:
                            TrailerItemView(item: trailer)
                                .padding(.horizontal)
                        case let screenshot as ScreenshotItem:
                            ScreenshotItemView(item: screenshot)
                                .padding(.horizontal)
                        default: fatalError()
                        }
                    }
                    
                    HStack {
                        Spacer()
                        Button(state.viewAllMedia) { state.onViewAllMediaClick() }
                            .padding()
                    }
                }
                
                if state.isTrailersVisible {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(state.trailersText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    ForEach(state.trailers, id: \.self) { trailer in
                        TrailerItemView(item: trailer)
                            .padding(.horizontal)
                    }
                    
                    HStack {
                        Spacer()
                        Button(state.viewAllTrailers) { state.onViewAllTrailersClick() }
                            .padding()
                    }
                }
                
                if state.isScreenshotsVisible {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(state.screenshotsText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    ForEach(state.screenshots, id: \.self) { screenshot in
                        ScreenshotItemView(item: screenshot)
                            .padding(.horizontal)
                    }
                    
                    HStack {
                        Spacer()
                        Button(state.viewAllScreenshots) { state.onViewAllScreenshotsClick() }
                            .padding()
                    }
                }
                
                if state.isReviewsVisible {
                    Text(state.reviewTitleText)
                        .font(.title)
                        .padding(.horizontal)
                    
                    ForEach(state.reviews, id: \.self) { review in
                        CardReviewItemView(item: review)
                            .padding(.horizontal)
                    }
                    
                    if state.isViewAllVisible {
                        HStack {
                            Spacer()
                            Button(state.viewAllText) { state.onViewAllReviewsClick() }
                                .padding()
                        }
                    }
                }
            }
        }
        .onAppear { state.onRefresh() }
    }
}

#Preview {
    GameDetailsContentView(
        state: GameDetailsContentKt.GameDetailsContent_PreviewData()
    )
}
