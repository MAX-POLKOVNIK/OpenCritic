//
//  GameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameListItemView: View {
    let item: GameListListItem
    
    var body: some View {
        VStack(alignment: .center) {
            ZStack(alignment: .leading) {
                let reversed = Array(item.posterUrls.reversed().enumerated())
                
                ForEach(reversed, id: \.0) { index, posterUrl in
                    let padding = ((item.posterUrls.count - 1 - index) * 64)
                    
                    ZStack(alignment: .leading) {
                        CachedAsyncImage(
                            url: URL(string: posterUrl),
                            urlCache: .imageCache
                        ) { image in
                            image.resizable()
                        } placeholder: {
                            Color.gray
                        }
                            .frame(width: 128, height: 192)
                            .clipShape(.rect(cornerRadius: 8))
                    }
                    .padding(.leading, CGFloat(padding))
                }
            }
            .frame(width: 320, height: 192, alignment: .leading)
            .padding()
            
            Text(item.nameText)
                .font(.title2)
                .bold()
            
            Text(item.gamesText)
            
            HStack {
                Button(item.shareButtonText, action: item.onShareClick)
                    .padding()
                
                Button(item.editButtonText, action: item.onEditClick)
                    .padding()
                    .disabled(true)
            }
        }
        .card()
        .onTapGesture { item.onClick() }
    }
}

#Preview {
    GameListItemView(
        item: GameListListItemKt.GameListListItem_PreviewData()
    )
}
