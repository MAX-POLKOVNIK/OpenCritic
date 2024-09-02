//
//  HallsOfFameContentView.swift
//  iosApp
//
//  Created by Max Polkovnik on 07/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HallsOfFameContentView: View {
    let content: HallsOfFameContent
    
    var body: some View {
        List {
            ForEach(content.lists, id: \.id) { list in
                HallOfFameGameListListItemView(item: list)
                    .listRowSeparator(.hidden)
                    .listRowInsets(EdgeInsets())
                    .padding(.top)
            }
        }
        .listStyle(.plain)
    }
}

#Preview {
    HallsOfFameContentView(
        content: HallsOfFameContent(
            lists: []
        )
    )
}
