//
//  HallOfFameGameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views

struct HallOfFameGameListItemView: View {
    let item: HallOfFameGameListItem
    
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
                    NoGamePosterView()
                        .frame(width: 128, height: 192)
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
            item.onClick()
        }
    }
}

#Preview {
    HallOfFameGameListItemView(
        item: HallOfFameGameListItemKt.HallOfFameGameListItem_PreviewData()
    )
}
