//
//  TrailerView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TrailerItemView: View {
    let item: TrailerItem
    
    var body: some View {
        VStack(alignment: .leading) {
            VStack {
                CachedAsyncImage(
                    url: URL(string: item.thumbnailUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                        .scaledToFill()
                        .clipped()
                } placeholder: {
                    Color.gray
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .aspectRatio(16/9, contentMode: .fill)
            .fixedSize(horizontal: false, vertical: true)
            
            Text(item.titleText)
                .padding()
        }
        .card()
        .onTapGesture {
            item.click()
        }
    }
}

#Preview {
    TrailerItemView(
        item: TrailerItem(
            titleText: "Trailer text",
            thumbnailUrl: "https://img.youtube.com/vi/uLN9qrJ8ESs/0.jpg",
            onClick: { _ in }
        )
    )
}
