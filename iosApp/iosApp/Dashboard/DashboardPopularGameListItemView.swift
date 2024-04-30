//
//  DashboardPopularGameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 30/04/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardPopularGameListItemView: View {
    let item: DashboardPopularGameListItem
    
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
                HStack(
                    content: {
                        Image(item.headImageResource)
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                            .frame(width: 24, height: 24)
                        Text(item.scoreText)
                            .bold()
                    }
                )
                Text(item.nameText)
                    .lineLimit(2)
                    .multilineTextAlignment(.leading)
                    .frame(maxWidth: 128, minHeight: 48, alignment: .topLeading)
                    .fixedSize(horizontal: false, vertical: /*@START_MENU_TOKEN@*/true/*@END_MENU_TOKEN@*/)
            }
        )
    }
}

struct DashboardPopularGameListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardPopularGameListItemView(
            item: DashboardPopularGameListItem(
                game: PopularGame(
                    id: 1,
                    name: "Test some long name",
                    posterUrl: "https://img.opencritic.com/game/14353/o/cDwMyHmW.jpg",
                    score: 32,
                    tier: Tier.fair
                ),
                imageResourceProvider: IosImagesResourceProvider(),
                onClick: {_ in }
            )
        )
        .previewLayout(.fixed(width: 300, height: 600))
    }
}
