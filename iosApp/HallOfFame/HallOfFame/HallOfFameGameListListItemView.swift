//
//  HallOfFameGameListListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HallOfFameGameListListItemView: View {
    let item: HallOfFameGameListListItem
    var body: some View {
        VStack(alignment: .leading) {
            Text(item.yearText)
                .font(.title)
                .padding(.horizontal)
                .padding(.top)
            
            ScrollView(.horizontal, showsIndicators: false) {
                LazyHStack(alignment: .top, spacing: 10) {
                    ForEach(item.games, id: \.id) { item in
                        HallOfFameGameListItemView(
                            item: item
                        )
                    }
                }
                .padding(.horizontal)
            }
        }
    }
}

#Preview {
    HallOfFameGameListListItemView(
        item: HallOfFameGameListListItemKt.HallOfFameGameListListItem_PreviewData()
    )
}
