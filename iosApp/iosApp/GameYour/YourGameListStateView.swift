//
//  YourGameListStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 05/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct YourGameListStateView: View {
    let state: YourGameListState
    
    @State private var selectedFilterItem = YourGameFilterItem(key: YourGameFilter.all, text: "All")
    
    init(state: YourGameListState) {
        self.state = state
    }
    
    var body: some View {
        List {
            Picker(state.filtersTitleText, selection: $selectedFilterItem) {
                ForEach(state.filterItems, id: \.self) {
                    Text($0.text)
                }
            }
            .pickerStyle(.menu)
            .onReceive(Just(selectedFilterItem)) { _ in
                state.selectFilterItem(selectedFilterItem)
            }
            .listRowSeparator(.hidden)
            
            ForEach(state.items, id: \.self) { item in
                YourGameListItemView(item: item)
                    .listRowInsets(.init(top: 5, leading: 20, bottom: 5, trailing: 16))
                    .listRowSeparator(.hidden)
            }
        }
        .listStyle(.plain)
        .onAppear { state.refresh() }
    }
}

#Preview {
    YourGameListStateView(
        state: YourGameListStateKt.YourGameListState_PreviewData()
    )
}
