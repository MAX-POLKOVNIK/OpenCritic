//
//  PeriodGameBrowserStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Mvvm

struct PeriodGameBrowserStateContentView: View {
    @Environment(\.horizontalSizeClass)
    private var horizontalSizeClass: UserInterfaceSizeClass?
    
    let state: PeriodGameBrowserContent
    
    var body: some View {
        if horizontalSizeClass == .compact {
            List {
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
        } else {
            ScrollView {
                LazyVGrid(
                    columns: [
                        .init(.flexible()),
                        .init(.flexible())
                    ]
                ) {
                    ForEach(state.browseGameItems, id: \.self) { item in
                        BrowseGameItemView(item: item)
                            .listRowSeparator(.hidden)
                    }
                    
                    if state.isLoadingItemVisible {
                        LoadingItemView(item: state.loadingItem)
                            .onAppear {
                                state.onLoadMore()
                            }
                    }
                }
                .padding(.horizontal)
            }
        }
    }
}

#Preview {
    PeriodGameBrowserStateContentView(
        state: PeriodGameBrowserContentKt.PeriodGameBrowserContent_PreviewData()
    )
}
