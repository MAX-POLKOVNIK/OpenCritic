//
//  DashboardStateContent.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardStateContentView: View {
    let state: DashboardStateContent
    
    var body: some View {
        ScrollView(.vertical) {
            LazyVStack(alignment: .leading) {
                DashboardTitleListItemView(item: state.popularGamesTitle)
                DashboardPopularGamesHorizontalListItemView(item: state.popularGames)
            }
        }
    }
}

#Preview {
    DashboardStateContentView(
        state: DashboardStateContent(
            popularGamesTitle:
                DashboardTitleListItem(
                    title: "Hello",
                    subtitle: "Second string"
                ),
            popularGames: 
                DashboardPopularGamesHorizontalListItem(
                    popularGames: [
                        PopularGame(
                            id: 1,
                            name: "Test some long",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            score: 32,
                            tier: Tier.fair
                        ),
                        PopularGame(
                            id: 2,
                            name: "Test",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            score: 32,
                            tier: Tier.fair
                        ),
                        PopularGame(
                            id: 3,
                            name: "Test",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            score: 32,
                            tier: Tier.fair
                        )
                    ],
                    imageResourceProvider: IosImagesResourceProvider(),
                    onClick: {_ in }
                )
        )
    )
}
