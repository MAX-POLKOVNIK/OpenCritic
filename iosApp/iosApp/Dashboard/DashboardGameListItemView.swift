//
//  DashboardGameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardGameListItemView: View {
    let item: DashboardGameListItem
    
    var body: some View {
        HStack {
            GameRankView(model: item.rank)
            Text(item.nameText)
                .lineLimit(1)
            Spacer()
            Text(item.dateText)
                .frame(minWidth: 70, alignment: .leading)
        }
            .padding(.horizontal)
            .contentShape(Rectangle())
            .onTapGesture { item.click() }
    }
}

struct DashboardGameListItemView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardGameListItemView(
            item: DashboardGameListItem(
                id: 1,
                rank: GameRankModel(
                    headImageResource: SharedImages.shared.fairHead, 
                    scoreText: "15"
                ),
                nameText: "Game ldskfhds  sdkjhfjsd ",
                dateText: "MAY 16",
                onClick: {_ in }
            )
        )
        .previewLayout(.fixed(width: 300, height: 50))
    }
}
