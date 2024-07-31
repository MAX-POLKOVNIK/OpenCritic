//
//  YourGameListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 05/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YourGameListItemView: View {
    let item: YourGameListItem
    
    var body: some View {
        HStack {
            Text(item.text)
                .lineLimit(2)
            
            Spacer()
            
            YourGameIndicatorSmallItemView(
                item: item.indicatorItem
            ).frame(width: 155, height: 48)
        }
        .contentShape(Rectangle())
        .onTapGesture { item.click() }
    }
}

#Preview {
    YourGameListItemView(
        item: YourGameListItemKt.YourGameListItem_PreviewData()
    )
}
