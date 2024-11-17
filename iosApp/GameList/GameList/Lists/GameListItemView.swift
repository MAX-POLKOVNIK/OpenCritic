//
//  GameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Views
internal import NukeUI

struct GameListItemView: View {
    let item: GameListListItem
    
    var body: some View {
        VStack(alignment: .center) {
            HStack {
                Spacer()
                ZStack(alignment: .leading) {
                    let reversed = Array(item.posterUrls.reversed().enumerated())
                    
                    ForEach(reversed, id: \.0) { index, posterUrl in
                        let padding = ((item.posterUrls.count - 1 - index) * 64)
                        
                        ZStack(alignment: .leading) {
                            LazyImage(url: URL(string: posterUrl)) { state in
                                if let image = state.image {
                                    image.resizable()
                                } else {
                                    NoGamePosterView()
                                        .frame(width: 128, height: 192)
                                }
                            }
                            .frame(width: 128, height: 192)
                            .clipShape(.rect(cornerRadius: 8))
                        }
                        .padding(.leading, CGFloat(padding))
                    }
                }
                .frame(width: 320, height: 192, alignment: .leading)
                .padding()
                
                Spacer()
            }
            
            
            Text(item.nameText)
                .font(.title2)
                .bold()
            
            Text(item.gamesText)
            
            HStack {
                if item.isShareButtonVisible {
                    Button(item.shareButtonText, action: item.shareClick)
                        .padding()
                }
                
                Button(item.editButtonText, action: item.editClick)
                    .padding()
                    .disabled(true)
            }
        }
        .card()
        .onTapGesture { item.click() }
    }
}

#Preview {
    GameListItemView(
        item: GameListListItemKt.GameListListItem_PreviewData()
    )
}
