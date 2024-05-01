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
                Spacer()
                    .frame(height: 16)
                DashboardTitleListItemView(item: state.dealsTitle)
                DashboardDealsHorizontalListItemView(item: state.deals)
                Spacer()
                    .frame(height: 16)
                DashboardSublistListitemView(item: state.reviewedToday)
                Spacer()
                    .frame(height: 24)
                DashboardSublistListitemView(item: state.recentlyReleased)
                Spacer()
                    .frame(height: 24)
                DashboardSublistListitemView(item: state.upcomingReleases)
                Spacer()
                    .frame(height: 24)
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
                ),
            dealsTitle: DashboardTitleListItem(
                title: "Deals",
                subtitle: "Deals description"
            ),
            deals: DashboardDealsHorizontalListItem(
                deals: [
                    GameDeal(
                        game: PopularGame(
                            id: 1,
                            name: "Test some long name",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            score: 32,
                            tier: Tier.fair
                        ),
                        name: "Amazon",
                        price: 49.99
                    ),
                    GameDeal(
                        game: PopularGame(
                            id: 2,
                            name: "Test some long name",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            score: 32,
                            tier: Tier.fair
                        ),
                        name: "Amazon",
                        price: 49.99
                    ),
                    GameDeal(
                        game: PopularGame(
                            id: 3,
                            name: "Test some long name",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            score: 32,
                            tier: Tier.fair
                        ),
                        name: "Amazon",
                        price: 49.99
                    ),
                ],
                stringResourceProvider: IosStringResourceProvider(),
                imageResourceProvider: IosImagesResourceProvider(),
                onClick: { _ in },
                onBuyNowClick: { _ in }
            ),
            recentlyReleased: DashboardSublistListItem(id: DashboardSublistListItem.Type_.recentlyreleased, titleText: "", items: [], viewMoreText: "", onMoreClick: { _ in }),
            upcomingReleases: DashboardSublistListItem(id: DashboardSublistListItem.Type_.recentlyreleased, titleText: "", items: [], viewMoreText: "", onMoreClick: { _ in }),
            reviewedToday: DashboardSublistListItem(id: DashboardSublistListItem.Type_.recentlyreleased, titleText: "", items: [], viewMoreText: "", onMoreClick: { _ in })
        )
    )
}
