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
    @Environment(\.horizontalSizeClass)
    private var horizontalSizeClass: UserInterfaceSizeClass?
    
    let state: YourGameListState
    
    init(state: YourGameListState) {
        self.state = state
    }
    
    var body: some View {
        ZStack {
            if horizontalSizeClass == .compact {
                List {
                    ForEach(state.items, id: \.self) { item in
                        GameListItemView(item: item)
                            .listRowInsets(.init(top: 5, leading: 20, bottom: 5, trailing: 16))
                            .listRowSeparator(.hidden)
                            .buttonStyle(BorderlessButtonStyle())
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
                        ForEach(state.items, id: \.self) { item in
                            GameListItemView(item: item)
                                .buttonStyle(BorderlessButtonStyle())
                        }
                    }
                    .padding(.horizontal)
                }
            }
            
            if (state.isLoginVisible) {
                VStack {
                    Button(state.loginText) {
                        state.onLoginClick()
                    }
                    Button(state.useOfflineText) {
                        state.onUseOfflineClick()
                    }
                    .padding(.top)
                }
            }
        }
        .onAppear { state.refresh() }
        .toolbar {
            if state.isActionVisible {
                Button(
                    action: state.onAction,
                    label: { Image(iconRes: state.actionIconResource) }
                )
            }
        }
    }
}

#Preview {
    YourGameListStateView(
        state: YourGameListStateKt.YourGameListState_PreviewData()
    )
}
