//
//  DashboardPopularGamesHorizontalListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardPosterGamesHorizontalListItemView: View {
    let item: DashboardPosterGamesHorizontalListItem
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(alignment: .top, spacing: 10) {
                ForEach(item.items, id: \.self) { item in
                    DashboardPosterGameListItemView(
                        item: item
                    )
                }
            }
            .padding(.horizontal)
        }
    }
}

struct DashboardPopularGamesHorizontalListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardPosterGamesHorizontalListItemView(
            item: DashboardPosterGamesHorizontalListItem(
                popularGames: [
                    PosterGame(
                        id: 1,
                        name: "Test some long name",
                        posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                        rank: GameRank(tier: Tier.fair, score: 32)
                    ),
                    PosterGame(
                        id: 2,
                        name: "Test some long name",
                        posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                        rank: GameRank(tier: Tier.fair, score: 32)
                    ),
                    PosterGame(
                        id: 3,
                        name: "Test some long name",
                        posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                        rank: GameRank(tier: Tier.fair, score: 32)
                    ),
                ],
                onClick: {_ in }
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
