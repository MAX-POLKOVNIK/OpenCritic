//
//  YourGameIndicatorSmallItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 05/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YourGameIndicatorSmallItemView: View {
    let item: YourGameIndicatorSmallItem
    
    var body: some View {
        HStack(spacing: 0) {
            Image(systemName: item.wantedImageResource)
                .resizable()
                .frame(width: 24, height: 24)
                .padding(8)
                .frame(minWidth: 0, maxWidth: .infinity)
                .foregroundColor(item.wantedIconColor?.color)
                .background(item.wantedBackgroundColor?.color)
                .contentShape(Rectangle())
                .onTapGesture { item.wantedClick() }
            
            Image(systemName: item.playedImageResource)
                .resizable()
                .frame(width: 24, height: 24)
                .padding(8)
                .frame(minWidth: 0, maxWidth: .infinity)
                .foregroundColor(item.playedIconColor?.color)
                .background(item.playedBackgroundColor?.color)
                .contentShape(Rectangle())
                .onTapGesture { item.playedClick() }
            
            Image(systemName: item.favoriteImageResource)
                .resizable()
                .frame(width: 24, height: 24)
                .padding(8)
                .frame(minWidth: 0, maxWidth: .infinity)
                .foregroundColor(item.favoriteIconColor?.color)
                .background(item.favoriteBackgroundColor?.color)
                .contentShape(Rectangle())
                .onTapGesture { item.favoriteClick() }
        }
        .card()
    }
}

#Preview {
    YourGameIndicatorSmallItemView(
        item: YourGameIndicatorSmallItemKt.YourGameIndicatorSmallItem_PreviewData(
            imageResourceProvider: IosImagesResourceProvider()
        )
    )
}
