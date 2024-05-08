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
                    .padding(.top)
                DashboardPosterGamesHorizontalListItemView(item: state.popularGames)
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
                DashboardTitleListItemView(item: state.hallOfFameTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.hallOfFame)
                Spacer()
                    .frame(height: 16)
                DashboardMightyManListItemView(item: state.whoIsMightyMan)
                Spacer()
                    .frame(height: 16)
                DashboardTitleListItemView(item: state.switchTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.switchGames)
                Spacer()
                    .frame(height: 16)
                DashboardTitleListItemView(item: state.xboxTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.xboxGames)
                Spacer()
                    .frame(height: 16)
                DashboardTitleListItemView(item: state.playstationTitle)
                DashboardPosterGamesHorizontalListItemView(item: state.playstationGames)
                Spacer()
                    .frame(height: 16)
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
                DashboardPosterGamesHorizontalListItem(
                    popularGames: [
                        PosterGame(
                            id: 1,
                            name: "Test some long",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            rank: GameRank(tier: Tier.fair, score: 32)
                        ),
                        PosterGame(
                            id: 2,
                            name: "Test",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            rank: GameRank(tier: Tier.fair, score: 32)
                        ),
                        PosterGame(
                            id: 3,
                            name: "Test",
                            posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                            rank: GameRank(tier: Tier.fair, score: 32)
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
                onClick: { _ in },
                onBuyNowClick: { _ in }
            ),
            recentlyReleased: DashboardSublistListItem(id: DashboardSublistListItem.Type_.recentlyreleased, titleText: "", items: [], viewMoreText: "", onMoreClick: { _ in }),
            upcomingReleases: DashboardSublistListItem(id: DashboardSublistListItem.Type_.recentlyreleased, titleText: "", items: [], viewMoreText: "", onMoreClick: { _ in }),
            reviewedToday: DashboardSublistListItem(id: DashboardSublistListItem.Type_.recentlyreleased, titleText: "", items: [], viewMoreText: "", onMoreClick: { _ in }),
            hallOfFameTitle: DashboardTitleListItem(
                title: "Hall of fame",
                subtitle: "Hall of fame description"
            ),
            hallOfFame: DashboardPosterGamesHorizontalListItem(popularGames: [], imageResourceProvider: IosImagesResourceProvider(), onClick: { _ in }),
            whoIsMightyMan: DashboardMightyManListItemKt.DashboardMightyManListItem(stringProvider: IosStringProvider(), imageResourceProvider: IosImagesResourceProvider()),
            switchTitle: DashboardTitleListItem(
                title: "Hall of fame",
                subtitle: "Hall of fame description"
            ),
            switchGames: DashboardPosterGamesHorizontalListItem(popularGames: [], imageResourceProvider: IosImagesResourceProvider(), onClick: { _ in }),
            xboxTitle: DashboardTitleListItem(
                title: "Hall of fame",
                subtitle: "Hall of fame description"
            ),
            xboxGames: DashboardPosterGamesHorizontalListItem(popularGames: [], imageResourceProvider: IosImagesResourceProvider(), onClick: { _ in }),
            playstationTitle: DashboardTitleListItem(
                title: "Hall of fame",
                subtitle: "Hall of fame description"
            ),
            playstationGames: DashboardPosterGamesHorizontalListItem(popularGames: [], imageResourceProvider: IosImagesResourceProvider(), onClick: { _ in })
        )
    )
}
