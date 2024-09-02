//
//  ReviewBriefListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ReviewBriefListItemView: View {
    let item: ReviewBriefListItem
    
    var body: some View {
        HStack {
            Text(item.nameText)
            Spacer()
            Text(item.scoreText)
        }
            .padding(.horizontal)
    }
}

#Preview {
    ReviewBriefListItemView(
        item: ReviewBriefListItem(nameText: "IGN", scoreText: "100 / 100")
    )
}
