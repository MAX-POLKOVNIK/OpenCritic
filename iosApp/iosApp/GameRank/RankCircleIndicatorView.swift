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
    let value: Int64
    let tier: Tier
    let isPercent: Bool

    @State var size: CGSize = .zero
    
    var body: some View {
        let colors: [SwiftUI.Color] = switch (tier, isPercent) {
        case (Tier.mighty, false): [
            Color(red: 231 / 255, green: 85 / 255, blue: 39 / 255)
        ]
        case (Tier.mighty, true): [
            Color(red: 224 / 255, green: 83 / 255, blue: 38 / 255),
            Color(red: 248 / 255, green: 218 / 255, blue: 123 / 255),
            Color(red: 224 / 255, green: 83 / 255, blue: 38 / 255),
        ]
        case (Tier.strong, false): [
            Color(red: 143 / 255, green: 28 / 255, blue: 175 / 255)
        ]
        case (Tier.strong, true): [
            Color(red: 112 / 255, green: 109 / 255, blue: 222 / 255),
            Color(red: 236 / 255, green: 119 / 255, blue: 194 / 255),
            Color(red: 112 / 255, green: 109 / 255, blue: 222 / 255)
        ]
        case (Tier.fair, false): [
            Color(red: 98 / 255, green: 159 / 255, blue: 203 / 255)
        ]
        case (Tier.fair, true): [
            Color(red: 89 / 255, green: 138 / 255, blue: 120 / 255),
            Color(red: 139 / 255, green: 197 / 255, blue: 251 / 255)
        ]
        case (Tier.weak, false): [
            Color(red: 139 / 255, green: 175 / 255, blue: 111 / 255)
        ]
        case (Tier.weak, true): [
            Color(red: 106 / 255, green: 128 / 255, blue: 85 / 255)
        ]
        default: [.black]
        }
        
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
                
                let trimStart = ((100.0 - Double(value)) / 200.0)
                let trimEnd = 1.0 - trimStart
                
                Circle()
                    .trim(from: trimStart, to: trimEnd)
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
                
                Text("\(value)\(isPercent ? "%" : "")")
                    .foregroundStyle(.white)
            }.onAppear {
                size = proxy.size
            } // just an empty container to triggers the onAppear
            
        }
    }
}

#Preview {
    RankCircleIndicatorView(
        value: 90, tier: .strong, isPercent: true
    )
}
