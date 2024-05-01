//
//  DashboardDealListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardDealListItemView: View {
    let item: DashboardDealListItem
    
    var body: some View {
        VStack {
            DashboardPopularGameListItemView(item: item.gameItem)
            Button(item.priceText) { item.buyNowClick() }
                .bold()
            Button(item.buyNowText) { item.buyNowClick() }
                .frame(maxWidth: 128, minHeight: 48)
                .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
        }
    }
}

struct DashboardDealListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardDealListItemView(
            item: DashboardDealListItem(
                gameDeal: GameDeal(
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
                stringResourceProvider: IosStringResourceProvider(),
                imageResourceProvider: IosImagesResourceProvider(),
                onClick: { _ in },
                onBuyNowClick: { _ in }
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
