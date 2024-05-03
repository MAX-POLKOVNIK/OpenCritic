//
//  RankCircleIndicatorView.swift
//  iosApp
//
//  Created by Max Polkovnik on 02/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RankCircleIndicatorView: View {
    let item: RankCircleIndicatorItem

    @State var size: CGSize = .zero
    
    var body: some View {
        let colors = item.colors.map { $0.toUIColor().toColor() }
        
        let gradient = AngularGradient(
            gradient: Gradient(
                colors: colors
            ),
            center: .center
        )
        
        GeometryReader { proxy in
            ZStack {
                Circle()
                    .frame(width: size.width, height: size.width)
                
                let trimStart = ((1.0 - item.progress) / 2)
                let trimEnd = 1.0 - trimStart
                
                Circle()
                    .trim(from: CGFloat(trimStart), to: CGFloat(trimEnd))
                    .stroke(
                        gradient,
                        style:
                            StrokeStyle(
                                lineWidth: size.width * 0.1,
                                lineCap: .round,
                                lineJoin: .round
                            )
                    )
                    .frame(width: size.width * 0.8, height: size.width * 0.9)
                    .padding(8)
                
                Text(item.scoreText)
                    .foregroundStyle(.white)
            }.onAppear {
                size = proxy.size
            } // just an empty container to triggers the onAppear
            
        }
    }
}

#Preview {
    RankCircleIndicatorView(
        item: RankCircleIndicatorItemKt.createCriticsRecommendIndicator(tier: Tier.mighty, score: 90)
    )
}
