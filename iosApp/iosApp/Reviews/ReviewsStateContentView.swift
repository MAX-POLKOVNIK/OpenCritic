//
//  ReviewStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ReviewsStateContentView: View {
    let state: ReviewsStateContent
    
    @State private var selection = "Default"
    
    init(state: ReviewsStateContent) {
        self.state = state
        
        selection = state.sortText
    }
    
    var body: some View {
        List {
            Text(state.titleText)
                .font(.title)
                .listRowSeparator(.hidden)
            
            ZStack(alignment: .bottomLeading) {
                CachedAsyncImage(
                    url: URL(string: state.imageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                        .scaledToFill()
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .aspectRatio(1.77, contentMode: .fill)
                        
                } placeholder: {
                    Color.gray
                }
                    
                Rectangle()
                    .foregroundColor(.clear)
                    .background(
                        LinearGradient(
                            gradient: Gradient(colors: [.clear, .black]),
                            startPoint: .top,
                            endPoint: .bottom
                        )
                    )
                
                HStack(alignment: .center) {
                    if state.isTierVisible {
                        Image(state.tierImageResource)
                            .resizable()
                            .scaledToFit()
                            .frame(width: 56, height: 56)
                            .padding(.top, 8)
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
                .padding(.bottom)
            }

            Text(state.rankedDescription)
                .listRowSeparator(.hidden)
            
            Picker(state.sortTitleText, selection: $selection) {
                ForEach(state.availableSorts, id: \.self) {
                    Text($0)
                }
            }
            .pickerStyle(.menu)
            .onSubmit {
                state.selectedSort(sort: selection)
            }
            
            ForEach(state.reviewItems, id: \.self) { item in
                ReviewListItemView(item: item)
                    .buttonStyle(BorderlessButtonStyle())
            }
            
            if state.isLoadingItemVisible {
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
                    .onAppear {
                        state.loadMore()
                    }
            }
        }
        .listStyle(.plain)
        
    }
}

#Preview {
    ReviewsStateContentView(
        state: ReviewsStateKt.ReviewsStateContent_PreviewData(imageResourceProvider: IosImagesResourceProvider())
    )
}
