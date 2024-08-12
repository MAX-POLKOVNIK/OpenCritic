//
//  GameBrowserContentCompactView.swift
//  iosApp
//
//  Created by Max Polkovnik on 09/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct GameBrowserContentCompactView: View {
    let state: GameBrowserContent

    @State private var sort: GameSortItem
    @State private var timeframe: TimeframeItem
    @State private var platform: PlatformItem
    @State private var isNextGenOnly: Bool
    
    init(state: GameBrowserContent) {
        self.state = state
        
        sort = state.sortText
        timeframe = state.timeframeText
        platform = state.platformText
        isNextGenOnly = state.isNextGenChecked
    }
    
    var body: some View {
        List {
            Picker(state.platformTitleText.text(), selection: $platform) {
                ForEach(state.platformsItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(platform)) { _ in
                state.onSelectedPlatform(platform)
            }
            .listRowSeparator(.hidden)
            
            Picker(state.timeframeTitleText.text(), selection: $timeframe) {
                ForEach(state.timeframeItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(timeframe)) { _ in
                state.onSelectedTimeframe(timeframe)
            }
            .listRowSeparator(.hidden)
            
            Picker(state.sortTitleText.text(), selection: $sort) {
                ForEach(state.sortItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(sort)) { _ in
                state.onSelectedSort(sort)
            }
            .listRowSeparator(.hidden)
            
            if state.isNextGenVisible {
                Toggle(state.nextGenTitle.text(), isOn: $isNextGenOnly)
                    .onReceive(Just(isNextGenOnly)) { _ in
                        state.onNextGenChecked(
                            KotlinBoolean(bool: isNextGenOnly)
                        )
                    }
            }
            
            ForEach(state.browseGameItems, id: \.self) { item in
                BrowseGameItemView(item: item)
                    .listRowSeparator(.hidden)
            }
            
            if state.isLoadingItemVisible {
                LoadingItemView(item: state.loadingItem)
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
    GameBrowserContentCompactView(
        state: GameBrowserContentKt.GameBrowserContent_PreviewData()
    )
}
