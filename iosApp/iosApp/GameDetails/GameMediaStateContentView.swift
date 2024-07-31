//
//  GameMediaStateContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameMediaStateContentView: View {
    let state : GameMediaStateContent
    
    var body: some View {
        ScrollView(.vertical) {
            LazyVStack(
                alignment: .leading
            ) {
                if state.isTrailersVisible {
                    Text(state.trailersText)
                        .font(.title)
                        .padding(.vertical)
                    
                    ForEach(state.trailers, id: \.self) { trailer in
                        TrailerItemView(item: trailer)
                    }
                }
                
                if state.isScreenshotsVisible {
                    Text(state.screenshotsText)
                        .font(.title)
                        .padding(.vertical)
                    
                    ForEach(state.screenshots, id: \.self) { screenshot in
                        ScreenshotItemView(item: screenshot)
                    }
                }
            }
            .padding(.horizontal)
            .navigationBarTitle(state.titleText, displayMode: .large)
        }
    }
}

#Preview {
    GameMediaStateContentView(
        state: GameMediaStateContent(
            navigationTitle: "Stellar Blade",
            titleText: "Stellar Blade Screenshots and Trailers".asTextSource(),
            isTrailersVisible: true,
            trailersText: "Trailers".asTextSource(),
            trailers: [
                TrailerItem(
                    titleText: "1Stellar Blade - Demo Teaser | PS5 Games",
                    thumbnailUrl: "https://img.youtube.com/vi/C3GLEPFrlhw/maxresdefault.jpg",
                    onClick: { _ in }
                ),
                TrailerItem(
                    titleText: "2Stellar Blade - Demo Teaser | PS5 Games",
                    thumbnailUrl: "https://img.youtube.com/vi/C3GLEPFrlhw/maxresdefault.jpg",
                    onClick: { _ in }
                ),
                TrailerItem(
                    titleText: "3Stellar Blade - Demo Teaser | PS5 Games",
                    thumbnailUrl: "https://img.youtube.com/vi/C3GLEPFrlhw/maxresdefault.jpg",
                    onClick: { _ in }
                )
            ],
            isScreenshotsVisible: true,
            screenshotsText: "Screenshots".asTextSource(),
            screenshots: [
                ScreenshotItem(
                    thumbnailUrl: "https://img.opencritic.com/game/16510/QtAqzcBg.jpg",
                    onClick: { _ in }
                ),
                ScreenshotItem(
                    thumbnailUrl: "https://img.opencritic.com/game/16510/yvRk5Rd3.jpg",
                    onClick: { _ in }
                ),
                ScreenshotItem(
                    thumbnailUrl: "https://img.opencritic.com/game/16510/IFgHIapN.jpg",
                    onClick: { _ in }
                )
            ]
        )
    )
}
