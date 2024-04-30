//
//  DashboardPopularGamesHorizontalListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardPopularGamesHorizontalListItemView: View {
    let item: DashboardPopularGamesHorizontalListItem
    
    var body: some View {
        ScrollView(.horizontal) {
            LazyHStack(alignment: .top, spacing: 10) {
                ForEach(item.items, id: \.self) { item in
                    DashboardPopularGameListItemView(
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
        DashboardPopularGamesHorizontalListItemView(
            item: DashboardPopularGamesHorizontalListItem(
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
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
