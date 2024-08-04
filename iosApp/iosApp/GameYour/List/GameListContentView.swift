//
//  GameListContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameListContentView: View {
    let content: GameListContent
    
    var body: some View {
        List {
            ForEach(content.items, id: \.self) { item in
                GameRowListItemView(item: item)
                    .listRowInsets(.init(top: 5, leading: 20, bottom: 5, trailing: 16))
                    .listRowSeparator(.hidden)
                    .buttonStyle(BorderlessButtonStyle())
            }
        }
        .listStyle(.plain)
    }
}

#Preview {
    GameListContentView(
        content: GameListContentKt.GameListContent_PreviewData()
    )
}
