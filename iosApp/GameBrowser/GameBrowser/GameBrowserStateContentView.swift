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
    @Environment(\.horizontalSizeClass)
    private var horizontalSizeClass: UserInterfaceSizeClass?
    
    let state: GameBrowserContent

    @State private var sort: GameSortItem
    @State private var timeframe: TimeframeItem
    @State private var platform: PlatformItem

    init(state: GameBrowserContent) {
        self.state = state
        
        sort = state.sortText
        timeframe = state.timeframeText
        platform = state.platformText
    }
    
    var body: some View {
        if horizontalSizeClass == .compact {
            GameBrowserContentCompactView(state: state)
                .toolbar {
                    if state.isActionVisible {
                        Button(
                            action: state.onAction,
                            label: { Image(iconRes: state.actionIconResource) }
                        )
                    }
                }
        } else {
            GameBrowserContentRegularView(state: state)
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
}

#Preview {
    GameBrowserStateContentView(
        state: GameBrowserContentKt
            .GameBrowserContent_PreviewData()
    )
}
