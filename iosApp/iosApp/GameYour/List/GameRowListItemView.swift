//
//  GameRowListItem.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameRowListItemView: View {
    let item: GameRowListItem
    
    var body: some View {
        HStack(alignment: .top) {
            CachedAsyncImage(
                url: URL(string: item.posterUrl),
                urlCache: .imageCache
            ) { image in
                image.resizable()
            } placeholder: {
                Color.gray
            }
                .frame(width: 64, height: 96)
                .clipShape(.rect(cornerRadius: 4))
            
            VStack(alignment: .leading) {
                Text(item.name)
                
                GameRankView(model: item.rank)
            }
            .padding(.vertical)
            
            Spacer()
        }
        .contentShape(Rectangle())
        .onTapGesture {
            item.onClick()
        }
    }
}

#Preview {
    GameRowListItemView(
        item: GameRowListItemKt.GameRowListItem_PreviewData()
    )
}
