//
//  GameDetailsStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameDetailsStateContentView: View {
    let state: GameDetailsStateContent
    
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
                
                HStack(spacing: 0) {
                    ForEach(state.gameActionItems, id: \.self) { action in
                        GameActionItemView(item: action)
                            .frame(minWidth: 0, maxWidth: .infinity)
                        
                        Divider()
                    }
                }
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .padding(.vertical)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.white)
                            .shadow(color: .gray, radius: 2, x: 0, y: 2)
                    )
                    .padding()
                
                VStack(
                    alignment: .leading
                ) {
                    Text(state.name)
                        .font(.title)
                        .bold()
                        .padding()
                
                    Text(state.companiesText)
                        .padding(.horizontal)
                    Text(state.releaseDateText)
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
                            
                            RankCircleIndicatorView(
                                value: state.topCriticScore?.int64Value ?? Int64(0),
                                tier: state.tier ?? Tier.fair,
                                isPercent: false
                            )
                                .frame(minWidth: 0, maxWidth: .infinity)
                            
                            RankCircleIndicatorView(
                                value: state.recommendedPercent?.int64Value ?? Int64(0),
                                tier: state.tier ?? Tier.fair,
                                isPercent: true
                            )
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
                            Button(state.viewAllText, action: {})
                                .padding()
                        }
                    }
                }
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.white)
                            .shadow(color: .gray, radius: 2, x: 0, y: 2)
                    )
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
                        Button(state.viewAllMedia, action: {})
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
                        Button(state.viewAllTrailers, action: {})
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
                        Button(state.viewAllScreenshots, action: {})
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
                            Button(state.viewAllText, action: {})
                                .padding()
                        }
                    }
                }
            }
        }.navigationTitle(state.name)
    }
}

#Preview {
    GameDetailsStateContentView(
        state: GameDetailsStateContent(
            isImageVisible: true,
            imageUrl: "https://img.opencritic.com/game/14353/a7GST4so.jpg",
            name: "Game title",
            gameActionItems: GameActionItemKt.gameActionItems(
                imageResourceProvider: IosImagesResourceProvider(),
                stringResourceProvider: IosStringResourceProvider(),
                isWanted: false,
                isPlayed: false,
                isFavorite: false,
                onClick: { _ in }
            ),
            companiesText: "Some companies",
            releaseDateText: "MAY 25, 2505",
            platformsText: "Playstation, Xbox, PC",
            isTierVisible: true,
            tier: Tier.fair,
            tierImageResource: IosImagesResourceProvider().fairMan,
            tierDescription: "Tier description",
            topCriticScore: 90,
            topCriticScoreDescription: "Top critic description",
            recommendedPercent: 90,
            criticsRecommendDescription: "Critics recommends",
            briefReviews: [
                ReviewBriefListItem(nameText: "IGN", scoreText: "100 / 100"),
                ReviewBriefListItem(nameText: "IGN", scoreText: "100 / 100"),
                ReviewBriefListItem(nameText: "IGN", scoreText: "100 / 100"),
                ReviewBriefListItem(nameText: "IGN", scoreText: "100 / 100"),
            ],
            isViewAllVisible: true,
            viewAllText: "View all 1000 reviews",
            isMediaVisible: false,
            mediaText: "",
            media: [],
            viewAllMedia: "",
            isTrailersVisible: false,
            trailersText: "",
            trailers: [],
            viewAllTrailers: "",
            isScreenshotsVisible: false,
            screenshotsText: "",
            screenshots: [],
            viewAllScreenshots: "",
            isReviewsVisible: false,
            reviewTitleText: "",
            reviews: []
        )
    )
}
