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
    
    init(state: YourGameListState) {
        self.state = state
    }
    
    var body: some View {
        ZStack {
            List {
                ForEach(state.items, id: \.self) { item in
                    GameListItemView(item: item)
                        .listRowInsets(.init(top: 5, leading: 20, bottom: 5, trailing: 16))
                        .listRowSeparator(.hidden)
                        .buttonStyle(BorderlessButtonStyle())
                }
            }
            .listStyle(.plain)
            
            
            if (state.isLoginVisible) {
                Button(state.loginText) {
                    state.onLoginClick()
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
