//
//  GameRankView.swift
//  iosApp
//
//  Created by Max Polkovnik on 01/05/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GameRankView: View {
    let model: GameRankModel?
    
    var body: some View {
        if let model = model {
            HStack(
                content: {
                    Image(model.headImageResource)
                        .resizable()
                        .aspectRatio(contentMode: .fit)
                        .frame(width: 24, height: 24)
                    Text(model.scoreText)
                        .bold()
                }
            )
                .frame(width: 56)
        } else {
            Text("?")
                .frame(width: 56)
        }
    }
}

struct GameRankView_Previews: PreviewProvider {
    static var previews: some View {
        GameRankView(model: GameRankModel(headImageResource: SharedImages.shared.fairHead, scoreText: "16"))
            .previewLayout(.fixed(width: 56, height: 24))
    }
}
