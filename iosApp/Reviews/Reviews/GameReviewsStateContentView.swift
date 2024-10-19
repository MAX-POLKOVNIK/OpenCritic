//
//  ReviewStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine
import Mvvm
import Views

struct GameReviewsStateContentView: View {
    let state: GameReviewsStateContent
    
    @State private var selection = ReviewSortItem(
        key: ReviewSorting.default_,
        name: "Default".asTextSource()
    )
    
    init(state: GameReviewsStateContent) {
        self.state = state
        
        selection = state.sortText
    }
    
    var body: some View {
        List {
            ZStack(alignment: .bottomLeading) {
                CachedAsyncImage(
                    url: URL(string: state.imageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                        .scaledToFill()
                        .aspectRatio(16 / 9, contentMode: .fit)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .clipped()
                } placeholder: {
                    NoGamePosterView()
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
                
                HStack(alignment: .center) {
                    if state.isTierVisible {
                        Image(state.tierImageResource)
                            .resizable()
                            .frame(width: 56, height: 56)
                    }
                    
                    if state.isTopScoreIndicatorVisible {
                        RankCircleIndicatorView(item: state.topScoreIndicator)
                            .frame(width: 56, height: 56)
                    }
                    
                    if state.isRecommendIndicatorVisible {
                        RankCircleIndicatorView(item: state.recommendScoreIndicator)
                            .frame(width: 56, height: 56)
                    }
                }
                .padding()
            }
            .listRowSeparator(.hidden)
            
            if state.isRankedDescriptionVisible {
                Text(state.rankedDescription)
                    .listRowSeparator(.hidden)
            }
            
            Picker(state.sortTitleText.text(), selection: $selection) {
                ForEach(state.availableSorts, id: \.self) {
                    Text($0.name)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(selection)) { _ in
                state.selectedSort(sort: selection)
            }
            
            ForEach(state.reviewItems, id: \.self) { item in
                ReviewListItemView(item: item)
                    .buttonStyle(BorderlessButtonStyle())
            }
            
            if state.isLoadingItemVisible {
                LoadingItemView(item: state.loadingItem)
                    .onAppear {
                        state.loadMore()
                    }
            }
        }
        .listStyle(.plain)
        .navigationBarTitle(state.titleText, displayMode: .large)
    }
}

#Preview {
    GameReviewsStateContentView(
        state: GameReviewsStateKt.GameReviewsStateContent_PreviewData()
    )
}
