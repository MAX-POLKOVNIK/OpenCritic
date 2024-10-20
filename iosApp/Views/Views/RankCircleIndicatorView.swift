//
//  RankCircleIndicatorView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared
import Resource

public struct RankCircleIndicatorView: View {
    private let item: RankCircleIndicatorItem
    
    public init(item: RankCircleIndicatorItem) {
        self.item = item
    }
    
    public var body: some View {
        let colors = item.colors.map { $0.toUIColor().toColor() }
        
        let gradient = AngularGradient(
            gradient: Gradient(
                colors: colors
            ),
            center: .center
        )
        
        let trimStart = ((1.0 - item.progress) / 2)
        let trimEnd = 1.0 - trimStart
        
        GeometryReader { proxy in
            ZStack {
                Circle()
                    .foregroundColor(item.backgroundColor.color)
                
                Circle()
                    .trim(from: CGFloat(trimStart), to: CGFloat(trimEnd))
                    .stroke(
                        gradient,
                        style:
                            StrokeStyle(
                                lineWidth: proxy.size.width * 0.1,
                                lineCap: .round,
                                lineJoin: .round
                            )
                    )
                    .frame(width: proxy.size.width * 0.8, height: proxy.size.width * 0.9)
                
                Text(item.scoreText)
                    .foregroundStyle(.white)
            }
        }
    }
}

struct RankCircleIndicatorView_Previews: PreviewProvider {
    static var previews: some View {
        RankCircleIndicatorView(
            item: RankCircleIndicatorItemKt.createCriticsRecommendIndicator(tier: Tier.mighty, score: 90)
        )
        .previewLayout(.fixed(width: 56, height: 56))
    }
}
