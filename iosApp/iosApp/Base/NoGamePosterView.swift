//
//  NoGamePosterView.swift
//  iosApp
//
//  Created by Max Polkovnik on 11/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoGamePosterView: View {
    var body: some View {
        GeometryReader { proxy in
            ZStack(alignment: .center) {
                Image(uiImage: MR.images.shared.open_critic_logo.toUIImage()!)
                    .resizable()
                    .colorMultiply(.gray)
                    .aspectRatio(1, contentMode: .fit)
                    .padding()
            }
            .frame(width: proxy.size.width, height: proxy.size.height)
        }
        .background(
            Color.clear
                .background(.ultraThinMaterial)
        )
    }
}

#Preview {
    NoGamePosterView()
}
