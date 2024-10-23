//
//  RatingReviewsStateContentView.swift
//  Reviews
//
//  Created by Max Polkovnik on 19/11/2024.
//

import SwiftUI
import shared
import Combine
import Mvvm
import Views
internal import NukeUI

struct RatingReviewsStateContentView: View {
    let state: RatingReviewsStateContent
    
    @State private var selectedSortItem: RatingReviewSortItem
    @State private var selectedTimeframeItem: RatingReviewTimeframeItem
    
    init(state: RatingReviewsStateContent) {
        self.state = state
        
        selectedSortItem = state.selectedSortItem
        selectedTimeframeItem = state.selectedTimeFrameItem
    }
    
    var body: some View {
        List {
            ZStack(alignment: .bottomLeading) {
                LazyImage(url: URL(string: state.imageUrl)) { state in
                    if let image = state.image {
                        image.resizable()
                            .scaledToFill()
                            .aspectRatio(16 / 9, contentMode: .fit)
                            .frame(minWidth: 0, maxWidth: .infinity)
                            .clipped()
                    } else {
                        NoGamePosterView()
                            .aspectRatio(16 / 9, contentMode: .fit)
                    }
                    
                }
            }
            .listRowSeparator(.hidden)
            
            Picker(state.timeFrameTitle.text(), selection: $selectedTimeframeItem) {
                ForEach(state.availableTimeFrames, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(selectedTimeframeItem)) { _ in
                state.selectedTimeFrame(timeframeItem: selectedTimeframeItem)
            }
            
            Picker(state.sortTitleText.text(), selection: $selectedSortItem) {
                ForEach(state.availableSorts, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(selectedSortItem)) { _ in
                state.selectedSort(sort: selectedSortItem)
            }
            
            ForEach(state.reviewItems, id: \.self) { item in
                RatingReviewListItemView(item: item)
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
    RatingReviewsStateContentView(
        state: RatingReviewsStateKt.RatingReviewsStateContent_PreviewData()
    )
}
