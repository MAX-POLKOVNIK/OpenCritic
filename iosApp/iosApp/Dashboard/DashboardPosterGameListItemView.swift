//
//  DashboardPopularGameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardPosterGameListItemView: View {
    let item: DashboardPosterGameListItem
    
    var body: some View {
        VStack(
            alignment: .leading,
            content: {
                CachedAsyncImage(
                    url: URL(string: item.posterUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                }
                    .frame(width: 128, height: 192)
                    .clipShape(.rect(cornerRadius: 8))
                
                GameRankView(
                    model: item.rank
                )
                Text(item.nameText)
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                    .frame(maxWidth: 128, minHeight: 48, alignment: .topLeading)
                    .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
            }
        )
        .onTapGesture {
            item.click()
        }
    }
}

struct DashboardPosterGameListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardPosterGameListItemView(
            item: DashboardPosterGameListItem(
                game: PosterGame(
                    id: 1,
                    name: "Test some long name",
                    posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    rank: GameRank(tier: Tier.fair, score: 32)
                ),
                onClick: {_ in }
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
