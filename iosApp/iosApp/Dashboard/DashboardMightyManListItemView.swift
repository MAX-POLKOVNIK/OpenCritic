//
//  DashboardMightyManListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DashboardMightyManListItemView: View {
    let item: DashboardMightyManListItem
    
    var body: some View {
        Divider()
        VStack(
            alignment: .leading
        ) {
            
            Text(item.title)
                .font(.title)
            Spacer()
                .frame(height: 16)
            Text(item.description_)
            Spacer()
                .frame(height: 16)
            Text(item.colorDescription)
            ForEach(item.items, id: \.self) { it in
                DashboardMightyManItemListItemView(item: it)
            }
            
        }
            .padding(.horizontal)
        Divider()
    }
}

#Preview {
    DashboardMightyManListItemView(
        item: DashboardMightyManListItemKt.DashboardMightyManListItem(
            stringProvider: IosStringProvider(),
            imageResourceProvider: IosImagesResourceProvider()
        )
    )
}
