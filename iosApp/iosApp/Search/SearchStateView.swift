//
//  SearchStateView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Combine

struct SearchStateView: View {
    let state: SearchState
    
    @State private var criteria: String = ""
    
    var body: some View {
        ScrollView {
            LazyVStack {
                TextField(
                        state.searchHint,
                        text: $criteria
                )
                    .onSubmit {
                        state.changeSearch(criteria: criteria)
                    }
                    .onReceive(Just(criteria)) { _ in
                        state.changeSearch(criteria: criteria)
                    }
                    .textInputAutocapitalization(.never)
                    .disableAutocorrection(true)
                    .padding()
                    .textFieldStyle(.roundedBorder)
                
                switch state.searchListItemsState {
                case let content as SearchItemsStateContent:
                    ForEach(content.items, id: \.self) { item in
                        SearchListItemView(item: item)
                            .padding(.horizontal)
                    }
                case _ as SearchItemsStateLoading:
                    DashboardStateLoadingView(state: DashboardStateLoading.shared)
                case let empty as SearchItemsStateEmpty:
                    DashboardStateErrorView(state: DashboardStateError(error: empty.message))
                case let error as SearchItemsStateError:
                    DashboardStateErrorView(state: DashboardStateError(error: error.message))
                default:
                    Spacer()
                }
            }
        }
    }
}

#Preview {
    SearchStateView(
        state: SearchState(
            searchText: "Game to find",
            searchHint: "Type something",
            searchListItemsState: SearchItemsStateContent(
                items: [
                    SearchListItem(
                        id: 1,
                        nameText: "Game name",
                        kindText: "Game",
                        kindColor: Colors.shared.Orange,
                        onClick: { _ in }
                    ),
                    SearchListItem(
                        id: 1,
                        nameText: "Some magazine",
                        kindText: "Outlet",
                        kindColor: Colors.shared.Cyan,
                        onClick: { _ in }
                    ),
                    SearchListItem(
                        id: 1,
                        nameText: "Max Polkovnik",
                        kindText: "Critic",
                        kindColor: Colors.shared.Purple,
                        onClick: { _ in }
                    ),
                ]
            ),
            onSearchChanged: { _, _ in }
        )
    )
}
