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
    let item: YourGameIndicatorItem
    var body: some View {
        HStack(spacing: 0) {
            VStack {
                Image(systemName: item.wantedImageResource)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 28, height: 28)
                    .foregroundColor(item.wantedTextColor?.color)
                
                Text(item.wantedText)
                    .foregroundColor(item.wantedTextColor?.color)
            }
            .padding(.vertical)
            .frame(minWidth: 0, maxWidth: .infinity)
            .background(item.wantedBackgroundColor.color)
            .contentShape(Rectangle())
            .onTapGesture { item.wantedClick() }
            
            Divider()
            
            VStack {
                Image(systemName: item.playedImageResource)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 28, height: 28)
                    .foregroundColor(item.playedTextColor?.color)
                
                Text(item.playedText)
                    .foregroundColor(item.playedTextColor?.color)
            }
            .padding(.vertical)
            .frame(minWidth: 0, maxWidth: .infinity)
            .background(item.playedBackgroundColor.color)
            .contentShape(Rectangle())
            .onTapGesture { item.playedClick() }
            
            Divider()
            
            VStack {
                Image(systemName: item.favoriteImageResource)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 28, height: 28)
                    .foregroundColor(item.favoriteTextColor?.color)
                
                Text(item.favoriteText)
                    .foregroundColor(item.favoriteTextColor?.color)
            }
            .padding(.vertical)
            .frame(minWidth: 0, maxWidth: .infinity)
            .background(item.favoriteBackgroundColor.color)
            .contentShape(Rectangle())
            .onTapGesture { item.favoriteClick() }
        }
        .card()
    }
}

#Preview {
    YourGameIndicatorItemView(
        item: YourGameIndicatorItemKt
            .YourGameIndicatorItem_PreviewData(
                imageResourceProvider: IosImagesResourceProvider()
            )
    )
}
