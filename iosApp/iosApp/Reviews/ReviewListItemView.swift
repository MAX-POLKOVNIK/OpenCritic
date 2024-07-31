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
                        .onTapGesture {
                            item.authorClick()
                        }
                    Text(item.outletText)
                        .italic()
                        .onTapGesture {
                            item.outletClick()
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
            
            HStack {
                if let scoreString = item.score as? ReviewScoreDisplayItemString {
                    Text(scoreString.value)
                        .bold()
                }
                
                if let scoreStars = item.score as? ReviewScoreDisplayItemStars {
                    HStack(spacing: 0) {
                        ForEach(0..<scoreStars.filledStars, id: \.self) { _ in
                            Image(iconRes: Icons.shared.starFilled)
                        }
                        ForEach(0..<scoreStars.halfStars, id: \.self) { _ in
                            Image(iconRes: Icons.shared.starHalf)
                        }
                        ForEach(0..<scoreStars.emptyStars, id: \.self) { _ in
                            Image(iconRes: Icons.shared.star)
                        }
                    }
                }
                
                Spacer()
                
                Text(item.dateText)
            }
            
            if item.isGameVisible {
                Button(item.gameText) { item.gameClick() }
                    .multilineTextAlignment(.leading)
                    .padding(.top)
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
