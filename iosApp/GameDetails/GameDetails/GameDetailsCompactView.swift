//
//  GameDetailsCompactView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct GameDetailsCompactView: View {
    let state: GameDetailsContent
    
    init(state: GameDetailsContent) {
        self.state = state
    }
    
    var body: some View {
        LazyVStack(alignment: .leading) {
            LazyVStack(spacing: 0) {
                ZStack(alignment: .topLeading) {
                    LazyImage(url: URL(string: state.squareImageUrl)) { state in
                        if let image = state.image {
                            image.resizable()
                                .scaledToFill()
                        } else {
                            NoGamePosterView()
                        }
                        
                    }
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .aspectRatio(1.0, contentMode: .fit)
                    
                    if state.isTierVisible {
                        Image(state.tierImageResource)
                            .resizable()
                            .scaledToFill()
                            .aspectRatio(1.0, contentMode: .fit)
                            .frame(width: 76, height: 76)
                            .padding(.top)
                            .padding(.leading)
                    }
                }
                
                YourGameIndicatorItemView(
                    item: state.yourGameIndicatorItem
                )
                
                if state.isTierVisible {
                    HStack(
                        alignment: .top
                    ) {
                        VStack {
                            RankCircleIndicatorView(item: state.topCriticScore)
                                .frame(width: 96, height: 96)
                            
                            Text(state.topCriticScoreDescription)
                                .multilineTextAlignment(.center)
                        }
                        .frame(maxWidth: .infinity)
                        
                        VStack {
                            RankCircleIndicatorView(item: state.recommendedPercent)
                                .frame(width: 96, height: 96)
                            
                            Text(state.criticsRecommendDescription)
                                .multilineTextAlignment(.center)
                        }
                        .frame(maxWidth: .infinity)
                        
                        VStack {
                            RankCircleIndicatorView(item: state.playerRating)
                                .frame(width: 96, height: 96)
                            
                            Text(state.playerRatingDescription)
                                .multilineTextAlignment(.center)
                        }
                        .frame(maxWidth: .infinity)
                    }
                    .fixedSize(horizontal: false, vertical: true)
                    .padding(.top)
                    .padding(.horizontal)
                }
                
                VStack(
                    alignment: .leading
                ) {
                    Spacer()
                        .frame(height: 16)
                    
                    ForEach(state.briefReviews, id: \.self) { item in
                        ReviewBriefListItemView(item: item)
                    }
                    
                    if state.isViewAllVisible {
                        HStack {
                            Spacer()
                            Button(state.viewAllText) { state.onViewAllReviewsClick() }
                                .padding(.vertical)
                        }
                    }
                }
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                    .padding(.horizontal)
            }
            .card()
            .padding(.all)
        }
        
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
}

#Preview {
    GameDetailsCompactView(
        state: GameDetailsContentKt.GameDetailsContent_PreviewData()
    )
}
