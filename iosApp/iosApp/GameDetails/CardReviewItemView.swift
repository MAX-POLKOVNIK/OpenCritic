//
//  CardReviewItemView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CardReviewItemView: View {
    let item: CardReviewItem
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                VStack(alignment: .leading) {
                    Text(item.outletText)
                        .font(.title2)
                    Text(item.authorText)
                }
                Spacer()
                CachedAsyncImage(
                    url: URL(string: item.outletThumbnailUrl),
                    urlCache: .imageCache
                ) { image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                }
                .frame(width: 56, height: 56)
                .clipShape(Circle())
            }
            
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
            
            Text(item.snippetText)
                .padding(.top)
            Button(item.readFullReviewText) { item.click() }
                .padding(.top)
        }
        .padding()
        .card()
    }
}

#Preview {
    CardReviewItemView(
        item: CardReviewItem(
            id: "",
            outletText: "GamesRadar+",
            authorText: "Sam Loveridge",
            outletThumbnailUrl: "https://img.opencritic.com/outlet/91/qqIbhWtu.jpg",
            score: ReviewScoreDisplayItemStars(
                filledStars: 4,
                halfStars: 1,
                emptyStars: 5
            ),
            snippetText: "Everything that made GT Sport so good, plus everything that made early Gran Turismo games so good. A simply stunning driving game and a superb showcase for PS5.",
            readFullReviewText: "Read full review",
            onClick: { _ in }
        )
    )
}
