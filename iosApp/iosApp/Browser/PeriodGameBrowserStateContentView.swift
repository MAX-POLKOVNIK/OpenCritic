//
//  PeriodGameBrowserStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct PeriodGameBrowserStateContentView: View {
    let state: PeriodGameBrowserStateContent
    
    var body: some View {
        List {
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
        .navigationBarTitle(state.titleText, displayMode: .large)
    }
}

#Preview {
    PeriodGameBrowserStateContentView(
        state: PeriodGameBrowserStateKt.PeriodGameBrowserStateContent_PreviewData()
    )
}
