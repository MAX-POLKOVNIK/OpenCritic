//
//  DashboardDealsHorizontalListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardDealsHorizontalListItemView: View {
    let item: DashboardDealsHorizontalListItem
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(alignment: .top, spacing: 10) {
                ForEach(item.items, id: \.self) { item in
                    DashboardDealListItemView(
                        item: item
                    )
                }
            }
            .padding(.horizontal)
        }
    }
}

struct DashboardDealsHorizontalListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardDealsHorizontalListItemView(
            item: DashboardDealsHorizontalListItem(
                deals: [
                    GameDeal(
                        game: PosterGame(
                            id: 1,
                            name: "Test some long name",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            rank: GameRank(tier: Tier.fair, score: 32)
                        ),
                        name: "Amazon",
                        price: 49.99,
                        externalUrl: ""
                    ),
                    GameDeal(
                        game: PosterGame(
                            id: 2,
                            name: "Test some long name",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            rank: GameRank(tier: Tier.fair, score: 32)
                        ),
                        name: "Amazon",
                        price: 49.99,
                        externalUrl: ""
                    ),
                    GameDeal(
                        game: PosterGame(
                            id: 3,
                            name: "Test some long name",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            rank: GameRank(tier: Tier.fair, score: 32)
                        ),
                        name: "Amazon",
                        price: 49.99,
                        externalUrl: ""
                    ),
                ],
                stringProvider: IosStringProvider(),
                imageResourceProvider: IosImagesResourceProvider(),
                onClick: {_ in },
                onBuyNowClick: { _ in }
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
