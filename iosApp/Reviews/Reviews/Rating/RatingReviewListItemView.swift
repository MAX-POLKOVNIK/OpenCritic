//
//  RatingReviewListItemView.swift
//  Reviews
//
//  Created by Max Polkovnik on 19/11/2024.
//

import SwiftUI
import shared

struct RatingReviewListItemView: View {
    let item: RatingReviewListItem
    
    var body: some View {
        VStack(
            alignment: .leading
        ) {
            HStack(
                alignment: .center,
                spacing: 0
            ) {
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
                
                Image(iconRes: item.icon)
                    .padding(.leading)
                
                Text(item.userName)
                    .padding(8)
            }
            
            Text(item.dateText)
            
            Text(item.userText)
                .padding(.vertical)
            
            HStack {
                Spacer()
                
                Button(item.readFullReviewText, action: {})
                    .disabled(true)
            }
        }
    }
}

#Preview {
    RatingReviewListItemView(
        item: RatingReviewListItemKt.RatingReviewListItem_PreviewData(id: "")
    )
}
