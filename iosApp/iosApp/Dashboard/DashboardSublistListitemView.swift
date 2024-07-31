//
//  DashboardSublistListitemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardSublistListitemView: View {
    let item: DashboardSublistListItem
    
    var body: some View {
        LazyVStack(
            alignment: .leading,
            content: {
                Text(item.titleText)
                    .bold()
                    .font(.title2)
                    .padding(.horizontal)
                    
                Divider()
                ForEach(item.items, id: \.self) { game in
                    DashboardGameListItemView(item: game)
                        .padding(.vertical, 1)
                    Divider()
                }
                HStack(
                    content: {
                        Spacer()
                        Button(item.viewMoreText, action: item.moreClick)
                            .padding(.horizontal)
                            .padding(.vertical, 1)
                    }
                )
            }
        )
    }
}

#Preview {
    DashboardSublistListitemView(
        item: DashboardSublistListItem(
            id: DashboardSublistListItem.Type_.recentlyreleased,
            titleText: "RECENTLY RELEASED".asTextSource(),
            items: [
                DashboardGameListItem(
                    id: 1,
                    rank: GameRankModel(
                        headImageResource: SharedImages.shared.fairHead,
                        scoreText: "15"
                    ),
                    nameText: "Game",
                    dateText: "MAY 16".asTextSource(),
                    onClick: {_ in }
                ),
                DashboardGameListItem(
                    id: 2,
                    rank: GameRankModel(
                        headImageResource: SharedImages.shared.fairHead,
                        scoreText: "15"
                    ),
                    nameText: "Game",
                    dateText: "MAY 16".asTextSource(),
                    onClick: {_ in }
                ),
                DashboardGameListItem(
                    id: 3,
                    rank: GameRankModel(
                        headImageResource: SharedImages.shared.fairHead,
                        scoreText: "15"
                    ),
                    nameText: "Game",
                    dateText: "MAY 16".asTextSource(),
                    onClick: {_ in }
                )
            ],
            viewMoreText: "View More".asTextSource(),
            onMoreClick: { _ in }
        )
    )
}
