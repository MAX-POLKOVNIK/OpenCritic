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
internal import NukeUI

struct HallOfFameGameListItemView: View {
    let item: HallOfFameGameListItem
    
    var body: some View {
        VStack(
            alignment: .leading,
            content: {
                LazyImage(url: URL(string: item.posterUrl)) { state in
                    if let image = state.image {
                        image.resizable()
                    } else {
                        NoGamePosterView()
                            .frame(width: 128, height: 192)
                    }
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

#Preview {
    HallOfFameGameListItemView(
        item: HallOfFameGameListItemKt.HallOfFameGameListItem_PreviewData()
    )
}
