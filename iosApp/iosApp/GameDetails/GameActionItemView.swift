//
//  GameActionItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameActionItemView: View {
    let item: GameActionItem
    
    var body: some View {
        VStack {
            Image(systemName: item.imageResource)
                .resizable()
                .scaledToFit()
                .frame(width: 28, height: 28)
            Text(item.text)
        }
    }
}

#Preview {
    GameActionItemView(
        item: GameActionItem(
            gameAction: GameAction.favorite,
            imageResource: IosStringProvider().gameActionFavorite,
            text: "Favorite",
            isSelected: false,
            onClick: { _ in }
        )
    )
}
