//
//  DashboardMightyManItemListItem.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardMightyManItemListItemView: View {
    let item: DashboardMightyManItemListItem
    
    var body: some View {
        HStack {
            Image(item.imageResource)
                .resizable()
                .aspectRatio(contentMode: .fit)
                .frame(width: 56, height: 56)
            
            VStack(
                alignment: .leading
            ) {
                Text(item.name)
                    .bold()
                Text(item.description_)
            }
        }
    }
}

#Preview {
    DashboardMightyManItemListItemView(
        item: DashboardMightyManItemListItemKt
            .DashboardMightyManItemListItem(
                tier: Tier.mighty
            )
    )
}
