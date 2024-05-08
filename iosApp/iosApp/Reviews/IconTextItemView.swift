//
//  IconTextItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 04/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct IconTextItemView: View {
    let item: IconTextItem
    
    var body: some View {
        HStack {
            Image(systemName: item.icon)
            Text(item.text)
            Spacer()
        }
    }
}

#Preview {
    IconTextItemView(
        item: IconTextItem(
            icon: "hand.thumbsup.fill",
            text: "55.6% of games recommended"
        )
    )
}
