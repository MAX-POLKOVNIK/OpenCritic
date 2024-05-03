//
//  ReviewListItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 03/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ReviewListItemView: View {
    let item: ReviewListItem
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                VStack(alignment: .leading) {
                    Text(item.authorText)
                        .font(.title2)
                        .onTapGesture { item.outletClick() }
                    Text(item.outletText)
                        .italic()
                        .onTapGesture {
                            item.authorClick()
                        }
                }
                
                Spacer()
                
                CachedAsyncImage(
                    url: URL(string: item.imageUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                }
                .frame(width: 56, height: 56)
                .clipShape(Circle())
                .onTapGesture { item.imageClick() }
            }
            
            if let scoreString = item.score as? ReviewScoreDisplayItemString {
                Text(scoreString.value)
                    .bold()
            }
            
            if let scoreStars = item.score as? ReviewScoreDisplayItemStars {
                HStack(spacing: 0) {
                    ForEach(0..<scoreStars.filledStars, id: \.self) { _ in
                        Image(systemName: "star.fill")
                    }
                    ForEach(0..<scoreStars.halfStars, id: \.self) { _ in
                        Image(systemName: "star.leadinghalf.filled")
                    }
                    ForEach(0..<scoreStars.emptyStars, id: \.self) { _ in
                        Image(systemName: "star")
                    }
                }
            }
            
            Text(item.snippetText)
                .padding(.top)
            Button(item.readFullReviewText) { item.click() }
                .padding(.top)
        }
    }
}

#Preview {
    ReviewListItemView(
        item: ReviewListItemKt.ReviewListItem_PreviewData(id: "1")
    )
}
