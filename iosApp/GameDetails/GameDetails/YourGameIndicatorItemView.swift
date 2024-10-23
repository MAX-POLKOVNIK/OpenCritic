//
//  YourGameIndicatorItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 05/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct YourGameIndicatorItemView: View {
    private let iconSize: CGFloat = 20
    private let actionsPadding: CGFloat = 10
    
    let item: YourGameIndicatorItem
    var body: some View {
        HStack(spacing: 0) {
            VStack {
                Image(iconRes: item.wantedImageResource)
                    .resizable()
                    .scaledToFit()
                    .frame(width: iconSize, height: iconSize)
                    .foregroundColor(item.wantedTextColor?.color)
                
                Text(item.wantedText)
                    .foregroundColor(item.wantedTextColor?.color)
            }
            .padding(.vertical, actionsPadding)
            .frame(minWidth: 0, maxWidth: .infinity)
            .background(item.wantedBackgroundColor.color)
            .contentShape(Rectangle())
            .onTapGesture { item.wantedClick() }
            
            Divider()
            
            VStack {
                Image(iconRes: item.playedImageResource)
                    .resizable()
                    .scaledToFit()
                    .frame(width: iconSize, height: iconSize)
                    .foregroundColor(item.playedTextColor?.color)
                
                Text(item.playedText)
                    .foregroundColor(item.playedTextColor?.color)
            }
            .padding(.vertical, actionsPadding)
            .frame(minWidth: 0, maxWidth: .infinity)
            .background(item.playedBackgroundColor.color)
            .contentShape(Rectangle())
            .onTapGesture { item.playedClick() }
            
            Divider()
            
            VStack {
                Image(iconRes: item.favoriteImageResource)
                    .resizable()
                    .scaledToFit()
                    .frame(width: iconSize, height: iconSize)
                    .foregroundColor(item.favoriteTextColor?.color)
                
                Text(item.favoriteText)
                    .foregroundColor(item.favoriteTextColor?.color)
            }
            .padding(.vertical, actionsPadding)
            .frame(minWidth: 0, maxWidth: .infinity)
            .background(item.favoriteBackgroundColor.color)
            .contentShape(Rectangle())
            .onTapGesture { item.favoriteClick() }
        }
    }
}

#Preview {
    YourGameIndicatorItemView(
        item: YourGameIndicatorItemKt
            .YourGameIndicatorItem_PreviewData()
    )
}
