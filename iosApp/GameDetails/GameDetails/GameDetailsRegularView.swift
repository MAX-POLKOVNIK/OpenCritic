//
//  GameDetailsRegularView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct GameDetailsRegularView: View {
    let state: GameDetailsContent
    
    var body: some View {
        HStack(
            alignment: .top,
            spacing: 0
        ) {
            VStack {
                ZStack(alignment: .topLeading) {
                    LazyImage(url: URL(string: state.bannerImageUrl)) { state in
                        if let image = state.image {
                            image.resizable()
                                .aspectRatio(16/9, contentMode: .fit)
                        } else {
                            NoGamePosterView()
                                .aspectRatio(16/9, contentMode: .fit)
                        }
                    }
                    
                    Image(state.tierImageResource)
                        .resizable()
                        .scaledToFill()
                        .aspectRatio(1.0, contentMode: .fit)
                        .frame(width: 56, height: 56)
                        .padding()
                }
                .frame(maxWidth: .infinity)
                
                Text(state.creatorsText)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .multilineTextAlignment(.leading)
                    .padding(.horizontal)
                Text(state.releaseText)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .multilineTextAlignment(.leading)
                    .padding(.horizontal)
                Text(state.platformsText)
                    .bold()
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .multilineTextAlignment(.leading)
                    .padding(.horizontal)
            }
            
            VStack {
                YourGameIndicatorItemView(
                    item: state.yourGameIndicatorItem
                )
                .frame(height: 56)
                .card(stroke: .gray)
                .padding()
                
                if state.isTierVisible {
                    HStack(
                        alignment: .top
                    ) {
                        VStack {
                            RankCircleIndicatorView(item: state.topCriticScore)
                                .frame(width: 72, height: 72)
                            
                            Text(state.topCriticScoreDescription)
                                .multilineTextAlignment(.center)
                        }
                        .frame(maxWidth: .infinity)
                        
                        VStack {
                            RankCircleIndicatorView(item: state.recommendedPercent)
                                .frame(width: 72, height: 72)
                            
                            Text(state.criticsRecommendDescription)
                                .multilineTextAlignment(.center)
                        }
                        .frame(maxWidth: .infinity)
                        
                        VStack {
                            RankCircleIndicatorView(item: state.playerRating)
                                .frame(width: 72, height: 72)
                            
                            Text(state.playerRatingDescription)
                                .multilineTextAlignment(.center)
                        }
                        .frame(maxWidth: .infinity)
                    }
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.top)
                    .padding(.horizontal)
                }
                
                VStack {
                    ForEach(state.briefReviews, id: \.self) { item in
                        ReviewBriefListItemView(item: item)
                    }
                
                    if state.isViewAllVisible {
                        HStack {
                            Spacer()
                            Button(state.viewAllText) { state.onViewAllReviewsClick() }
                                .padding(.top)
                        }
                    }
                }
                .padding()
                
            }
            .frame(maxWidth: .infinity)
        }
        .card()
        .padding()
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

