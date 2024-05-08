//
//  GameBrowserStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct GameBrowserStateContentView: View {
    let state: GameBrowserStateContent

    @State private var sort = GameSortItem(key: GameSorting.score, text: "Score")
    @State private var timeframe = TimeframeItem(key: GameTimeframeAllTIme.shared, text: "All Time")
    @State private var platform = PlatformItem(key: nil, text: "All Platforms")

    init(state: GameBrowserStateContent) {
        self.state = state
        
        sort = state.sortText
        timeframe = state.timeframeText
        platform = state.platformText
    }
    
    var body: some View {
        List {
            Picker(state.platformTitleText, selection: $platform) {
                ForEach(state.platformsItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(platform)) { _ in
                state.onSelectedPlatform(platform)
            }
            .listRowSeparator(.hidden)
            
            Picker(state.timeframeTitleText, selection: $timeframe) {
                ForEach(state.timeframeItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(timeframe)) { _ in
                state.onSelectedTimeframe(timeframe)
            }
            .listRowSeparator(.hidden)
            
            Picker(state.sortTitleText, selection: $sort) {
                ForEach(state.sortItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(sort)) { _ in
                state.onSelectedSort(sort)
            }
            .listRowSeparator(.hidden)
            
            ForEach(state.browseGameItems, id: \.self) { item in
                BrowseGameItemView(item: item)
                    .listRowSeparator(.hidden)
            }
            
            if state.isLoadingItemVisible {
                DashboardStateLoadingView(state: DashboardStateLoading.shared)
                    .onAppear {
                        state.onLoadMore()
                    }
                    .listRowSeparator(.hidden)
            }
        }
        .listStyle(.plain)
    }
}

#Preview {
    GameBrowserStateContentView(
        state: GameBrowserStateKt
            .GameBrowserStateContent_PreviewData()
    )
}
